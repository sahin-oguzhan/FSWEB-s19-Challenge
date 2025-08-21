package com.workintech.twitterclone.dto.user;

public record UserPatchRequestDto(String username, String firstName, String lastName, String email, String password) {
}
