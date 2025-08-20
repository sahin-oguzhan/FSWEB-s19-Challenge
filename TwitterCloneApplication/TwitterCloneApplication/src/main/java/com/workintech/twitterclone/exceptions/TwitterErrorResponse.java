package com.workintech.twitterclone.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TwitterErrorResponse {
    private String message;
    private int status;
    private long timestamp;
}
