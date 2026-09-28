package com.ancyracademy.esportsclash.auth.application.exceptions;

import com.ancyracademy.esportsclash.core.domain.exceptions.BadRequestException;

public class EmailAddressUnavailableException extends BadRequestException {
    public EmailAddressUnavailableException(String message) {
        super("Email address is already in use");
    }
}
