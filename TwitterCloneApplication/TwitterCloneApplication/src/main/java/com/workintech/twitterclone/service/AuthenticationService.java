package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.user.LoginRequestDto;
import com.workintech.twitterclone.dto.user.UserRequestDto;
import com.workintech.twitterclone.dto.user.UserResponseDto;
import com.workintech.twitterclone.entity.Role;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.exceptions.TweetNotFoundException;
import com.workintech.twitterclone.exceptions.TwitterException;
import com.workintech.twitterclone.mapper.UserMapper;
import com.workintech.twitterclone.repository.RoleRepository;
import com.workintech.twitterclone.repository.UserRepository;
import com.workintech.twitterclone.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final JwtUtil jwtUtil;

    public UserResponseDto register(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.password());
        Role userRole = roleRepository.findByAuthority("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setEmail(userRequestDto.email());
        user.setPassword(encodedPassword);
        user.setFirstName(userRequestDto.firstName());
        user.setLastName(userRequestDto.lastName());
        user.setAuthorities(roles);

        userRepository.save(user);

        return userMapper.toResponse(user);
    }

    public String loginAndGetToken(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsernameOrEmail(loginRequestDto.username(), loginRequestDto.email()).orElseThrow(() -> new TwitterException("User not found!", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())){
            throw new TwitterException("Wrong password", HttpStatus.BAD_REQUEST);
        }


        return jwtUtil.generateToken(user);
    }
}
