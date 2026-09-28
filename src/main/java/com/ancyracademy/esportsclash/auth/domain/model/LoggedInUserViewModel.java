package com.ancyracademy.esportsclash.auth.domain.model;

public class LoggedInUserViewModel {

    private   String id;

    private   String emailAddress;

    private String token;



    public LoggedInUserViewModel(String id, String emailAddress,String token) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.token = token;
    }

    public LoggedInUserViewModel() {

    }

    public String getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getToken() {
        return token;
    }
}
