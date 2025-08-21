package com.workintech.twitterclone.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRequestDto(@NotNull
                             @NotEmpty
                             @NotBlank
                             String username,
                             @NotNull
                             @NotEmpty
                             @NotBlank
                             String firstName,
                             @NotNull
                             @NotEmpty
                             @NotBlank
                             String lastName,
                             @NotNull
                             @NotEmpty
                             @NotBlank
                             String email,
                             @NotNull
                             @NotEmpty
                             @NotBlank
                             String password
                             ){}
