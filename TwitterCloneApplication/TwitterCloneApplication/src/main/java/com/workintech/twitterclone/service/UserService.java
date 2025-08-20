package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.UserPatchRequestDto;
import com.workintech.twitterclone.dto.UserRequestDto;
import com.workintech.twitterclone.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();
    UserResponseDto findById(Long id);
    UserResponseDto create(UserRequestDto userRequestDto);
    UserResponseDto createOrReplace(Long id, UserRequestDto userRequestDto);
    UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto);
    void delete(Long id);
}
