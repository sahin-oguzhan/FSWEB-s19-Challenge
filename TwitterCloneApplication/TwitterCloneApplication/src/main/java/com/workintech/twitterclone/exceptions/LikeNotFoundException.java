package com.workintech.twitterclone.exceptions;

import org.springframework.http.HttpStatus;

public class LikeNotFoundException extends TwitterException {
    public LikeNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
