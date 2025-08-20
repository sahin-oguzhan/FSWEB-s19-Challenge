package com.workintech.twitterclone.controller;

import com.workintech.twitterclone.dto.UserPatchRequestDto;
import com.workintech.twitterclone.dto.UserRequestDto;
import com.workintech.twitterclone.dto.UserResponseDto;
import com.workintech.twitterclone.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/use")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> findAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@Positive @PathVariable Long id){
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponseDto create(@Validated @RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto replaceOrCreate(@PathVariable Long id,
                                           @Validated @RequestBody UserRequestDto userRequestDto){
        return userService.createOrReplace(id, userRequestDto);
    }

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
