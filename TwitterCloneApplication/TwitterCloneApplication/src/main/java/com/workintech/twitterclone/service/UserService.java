package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.user.UserPatchRequestDto;
import com.workintech.twitterclone.dto.user.UserRequestDto;
import com.workintech.twitterclone.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();
    UserResponseDto findById(Long id);
    UserResponseDto create(UserRequestDto userRequestDto);
    UserResponseDto createOrReplace(Long id, UserRequestDto userRequestDto);
    UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto);
    void delete(Long id);
}
