package com.workintech.twitterclone.controller;

import com.workintech.twitterclone.dto.user.LoginRequestDto;
import com.workintech.twitterclone.dto.user.LoginResponseDto;
import com.workintech.twitterclone.dto.user.UserRequestDto;
import com.workintech.twitterclone.dto.user.UserResponseDto;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.exceptions.TwitterException;
import com.workintech.twitterclone.mapper.UserMapper;
import com.workintech.twitterclone.service.AuthenticationService;
import com.workintech.twitterclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationService authenticationService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto){
       return authenticationService.register(userRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        User user = userService.findByUsernameOrEmail(loginRequestDto.username(), loginRequestDto.email());

        if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())){
            throw new TwitterException("Wrong password", HttpStatus.BAD_REQUEST);
        }

        String token = authenticationService.loginAndGetToken(loginRequestDto);
        return new LoginResponseDto(token);
    }
}
