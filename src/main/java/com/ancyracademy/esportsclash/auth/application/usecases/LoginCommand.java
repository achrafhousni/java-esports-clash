package com.ancyracademy.esportsclash.auth.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.auth.domain.model.LoggedInUserViewModel;

public class LoginCommand implements Command<LoggedInUserViewModel> {

    private String emailAddress;
    private String password;

    public LoginCommand() {
    }

    public LoginCommand(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }


}
