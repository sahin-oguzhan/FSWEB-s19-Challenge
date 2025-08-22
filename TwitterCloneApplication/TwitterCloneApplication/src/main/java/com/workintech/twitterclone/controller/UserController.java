package com.workintech.twitterclone.controller;

import com.workintech.twitterclone.dto.user.UserPatchRequestDto;
import com.workintech.twitterclone.dto.user.UserRequestDto;
import com.workintech.twitterclone.dto.user.UserResponseDto;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.mapper.UserMapper;
import com.workintech.twitterclone.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> findAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@Positive @PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/{username}/{email}")
    public UserResponseDto findByUsernameOrEmail(@PathVariable String username, @PathVariable String email){
        User user = userService.findByUsernameOrEmail(username, email);
        return userMapper.toResponse(user);
    }

    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponseDto create(@Validated @RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }

    @Transactional
    @PutMapping("/{id}")
    public UserResponseDto replaceOrCreate(@PathVariable Long id,
                                           @Validated @RequestBody UserRequestDto userRequestDto){
        return userService.createOrReplace(id, userRequestDto);
    }

    @Transactional
    @PatchMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id,
                                  @Validated @RequestBody UserPatchRequestDto userPatchRequestDto){
        return userService.update(id, userPatchRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@Positive @PathVariable Long id){
        userService.delete(id);
    }
}
