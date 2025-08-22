package com.workintech.twitterclone.controller;

import com.workintech.twitterclone.dto.tweet.TweetRequestDto;
import com.workintech.twitterclone.dto.tweet.TweetResponseDto;
import com.workintech.twitterclone.entity.Tweet;
import com.workintech.twitterclone.service.TweetService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {
    @Autowired
    private final TweetService tweetService;

    @GetMapping
    public List<TweetResponseDto> findAll(){
        return tweetService.getAll();
    }

    @GetMapping("/{username}")
    public List<TweetResponseDto> findByUserName(@Validated @PathVariable String username){
        return tweetService.findByUserName(username);
    }

    @GetMapping("/{userId}")
    public List<TweetResponseDto> findByUserId(@Positive @PathVariable Long userId){
        return tweetService.findByUserId(userId);
    }

    @PostMapping
    public TweetResponseDto create(@Validated @RequestBody TweetRequestDto tweetRequestDto){
        return tweetService.create(tweetRequestDto);
    }

    @PutMapping("/{id}")
    public TweetResponseDto update(@Positive @PathVariable Long id, @Validated @RequestBody TweetRequestDto tweetRequestDto){
        return tweetService.update(id, tweetRequestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@Positive @PathVariable Long id){
        tweetService.delete(id);
    }
}
