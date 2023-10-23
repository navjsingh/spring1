package com.navi.repository;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import com.navi.model.Portfolio;
import com.navi.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {        // Our "database" of users
        users.add(new User("123@gmail.com", "123", null));
        users.add(new User("jared@gmail.com", "123", null));
        users.add(new User("nabil@gmail.com", "123", null));
        users.add(new User("matt@gmail.com", "123", null));
        users.add(new User("gianluca@gmail.com", "123", null));
    }

    public void updateEmail(User user, String newEmail) {
        user.setEmail(newEmail);
    }

    public void updatePassword(User user, String password) {
        user.setPassword(password);
    }

    public boolean hasPortfolio(User user) {
        if (user.getPortfolioId() == null) {
            return false;
        }
        return true;
    }

    public User assignPortfolio(User user, String portfolioId) { 
        user.setPortfolio(portfolioId);
        return user;
    }

    public User addUser(User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                return null;
            }
        }
        users.add(user);
        return user;
    }

    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User deleteUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }

}
