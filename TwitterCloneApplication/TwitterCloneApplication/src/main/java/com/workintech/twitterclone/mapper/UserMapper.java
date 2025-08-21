package com.workintech.twitterclone.mapper;

import com.workintech.twitterclone.dto.user.UserPatchRequestDto;
import com.workintech.twitterclone.dto.user.UserRequestDto;
import com.workintech.twitterclone.dto.user.UserResponseDto;
import com.workintech.twitterclone.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setFirstName(userRequestDto.firstName());
        user.setLastName(userRequestDto.lastName());
        user.setEmail(userRequestDto.email());
        user.setPassword(userRequestDto.password());
        user.setRegistrationDate(LocalDate.now());
        return user;
    }

    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(user.getUsername());
    }

    public User updateEntity(User userToUpdate, UserPatchRequestDto userPatchRequestDto) {
        if (userPatchRequestDto.username() != null)
            userToUpdate.setUsername(userPatchRequestDto.username());

        if (userPatchRequestDto.firstName() != null)
            userToUpdate.setFirstName(userPatchRequestDto.firstName());

        if (userPatchRequestDto.lastName() != null)
            userToUpdate.setLastName(userPatchRequestDto.lastName());

        if (userPatchRequestDto.email() != null)
            userToUpdate.setEmail(userPatchRequestDto.email());

        if (userPatchRequestDto.password() != null)
            userToUpdate.setPassword((userPatchRequestDto.password()));
        return userToUpdate;
    }
}
