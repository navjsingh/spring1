package com.navi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navi.model.Portfolio;
import com.navi.model.User;
import com.navi.repository.UserRepository;

@Service
public class UserService {

    @Autowired 
    private UserRepository userRepository;
    
    public User updateEmail(String oldEmail, String newEmail) {
        User user = userRepository.getUser(oldEmail);
        if (user == null) {
            return null;
        } else {
            userRepository.updateEmail(user, newEmail);
            return user;
        }
    }

    public User updatePassword(String email, String password) {
        User user = userRepository.getUser(email);
        if (user == null) {
            return null;
        } else {
            userRepository.updatePassword(user, password);
            return user;
        }
    }

    public boolean hasPortfolio(String email) {
        User user = userRepository.getUser(email);
        if (user == null) {
            return false;
        } else {
            return userRepository.hasPortfolio(user);
        }
    }

    public User assignPortfolio(String email, String portfolioId) {
        User user = userRepository.getUser(email);
        if (user == null) {
            return null;
        } else {
            if (userRepository.assignPortfolio(user, portfolioId) == null) {
                return null;
            };
            return user;
        }
    }

    public User addUser(String email, String password, String portfolioId) {
        User user = new User(email, password, portfolioId);
        userRepository.addUser(user);
        return user;
    }

    public User getUser(String email) {
        return userRepository.getUser(email);
    }

    public User deleteUser(String email) {
        return userRepository.deleteUser(email);
    }
}
