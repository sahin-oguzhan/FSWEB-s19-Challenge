package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.retweet.RetweetRequestDto;
import com.workintech.twitterclone.dto.retweet.RetweetResponseDto;
import com.workintech.twitterclone.entity.Retweet;

import java.util.List;

public interface RetweetService {
    public List<RetweetResponseDto> findAll();
    public RetweetResponseDto findById(Long id);
    public RetweetResponseDto createRetweet(RetweetRequestDto retweetRequestDto, Long userId, Long tweetId);
    public RetweetResponseDto updateRetweet(Long retweetId, RetweetRequestDto retweetRequestDto);
    public void deleteRetweet(Long retweetId);
}
