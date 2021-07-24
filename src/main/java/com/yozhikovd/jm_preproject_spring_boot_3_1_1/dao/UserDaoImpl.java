package com.yozhikovd.jm_preproject_spring_boot_3_1_1.dao;

import com.yozhikovd.jm_preproject_spring_boot_3_1_1.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao  {

    @PersistenceContext
    EntityManager entityManager;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> userList() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public User getUserById(int id) {
        return userList().stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void addNewUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        entityManager.persist(user);
        System.out.println("Пользователь с именем " + user.getLastName() + user.getName() +" успешно добавлен");
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        User userToDelete = getUserById(id);
        entityManager.remove(userToDelete);
    }

    @Override
    public User findByUsername(String username) {
        return userList().stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }

}
