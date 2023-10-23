package com.navi.model;

import lombok.NonNull;

public class User {

    @NonNull                // Lombok annotation that will check if field is null upon creation
    private String email;   // No need for id in user as email is unique

    @NonNull
    private String password;

    private String portfolioId;

    public User() {
    }

    public User(String email, String password, String portfolioId) {
        this.email = email;
        this.password = password;
        this.portfolioId = portfolioId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolio(String portfolioId) {
        this.portfolioId = portfolioId;
    }
}