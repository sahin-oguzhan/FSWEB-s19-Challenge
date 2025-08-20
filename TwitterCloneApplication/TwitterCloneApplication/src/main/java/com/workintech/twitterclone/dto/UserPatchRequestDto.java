package com.workintech.twitterclone.dto;

import java.time.LocalDate;

public record UserPatchRequestDto(String username, String firstName, String lastName, String email, String password) {
}
