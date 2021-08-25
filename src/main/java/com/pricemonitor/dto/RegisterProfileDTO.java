package com.pricemonitor.dto;


public class RegisterProfileDTO {
    String fullName;
    String login;
    String password;
    String email;

    public RegisterProfileDTO(String fullName, String login, String password, String email) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public RegisterProfileDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
