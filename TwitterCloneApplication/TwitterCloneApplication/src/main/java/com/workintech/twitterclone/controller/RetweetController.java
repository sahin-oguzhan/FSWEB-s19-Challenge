package com.workintech.twitterclone.controller;

import com.workintech.twitterclone.dto.retweet.RetweetRequestDto;
import com.workintech.twitterclone.dto.retweet.RetweetResponseDto;
import com.workintech.twitterclone.service.RetweetService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retweet")
@RequiredArgsConstructor
public class RetweetController {
    private final RetweetService retweetService;

    @GetMapping
    public List<RetweetResponseDto> findAll(){
        return retweetService.findAll();
    }

    @GetMapping("/{id}")
    public RetweetResponseDto findById(@Positive @PathVariable Long id){
        return retweetService.findById(id);
    }

    @Transactional
    @PostMapping("/{userId}/{tweetId}")
    public RetweetResponseDto createRetweet(@Validated @RequestBody RetweetRequestDto retweetRequestDto,@Positive @PathVariable Long userId,@Positive @PathVariable Long tweetId){
        return retweetService.createRetweet(retweetRequestDto, userId, tweetId);
    }

    @Transactional
    @PatchMapping("/{retweetId}")
    public RetweetResponseDto updateRetweet(@Positive @PathVariable Long retweetId, @Validated @RequestBody RetweetRequestDto retweetRequestDto){
        return retweetService.updateRetweet(retweetId, retweetRequestDto);
    }

    @Transactional
    @DeleteMapping("/{retweetId}")
    public void deleteRetweet(@Positive @PathVariable Long retweetId){
        retweetService.deleteRetweet(retweetId);
    }
}
