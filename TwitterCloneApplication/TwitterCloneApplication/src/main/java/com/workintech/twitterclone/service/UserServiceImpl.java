package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.user.UserPatchRequestDto;
import com.workintech.twitterclone.repository.UserRepository;
import com.workintech.twitterclone.dto.user.UserRequestDto;
import com.workintech.twitterclone.dto.user.UserResponseDto;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.exceptions.UserNotFoundException;
import com.workintech.twitterclone.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
                    return userMapper.toResponse(user.get());
                }
                throw new UserNotFoundException("User not found with id: " + id);
    }

    public User findByUsernameOrEmail(String username, String email){
        Optional<User> user = userRepository.findByUsernameOrEmail(username, email);
        if (user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException("User not found with username: " + username + " and email: " + email);
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDto createOrReplace(Long id, UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userMapper.toResponse(userRepository.save(user));
        }
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserPatchRequestDto userPatchRequestDto) {
        User userToUpdate = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userToUpdate = userMapper.updateEntity(userToUpdate, userPatchRequestDto);
        userToUpdate.setPassword(passwordEncoder.encode(userPatchRequestDto.password()));

        return userMapper.toResponse(userRepository.save(userToUpdate));

    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Username not found: " + username));
    }
}
