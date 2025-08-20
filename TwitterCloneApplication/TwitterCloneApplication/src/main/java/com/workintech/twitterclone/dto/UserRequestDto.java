package com.workintech.twitterclone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public record UserRequestDto(@NotNull
                             @NotEmpty
                             @NotBlank
                             @JsonProperty("user_name")
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
                             String password,
                             LocalDate registrationDate
                             ){}
