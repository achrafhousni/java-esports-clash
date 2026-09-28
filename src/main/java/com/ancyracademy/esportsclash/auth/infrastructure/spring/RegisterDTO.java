package com.ancyracademy.esportsclash.auth.infrastructure.spring;

public class RegisterDTO {
    private String emailAddress;
    private String password;

    public RegisterDTO() {
    }

    public RegisterDTO(String email, String password) {
        this.emailAddress = email;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
