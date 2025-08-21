package com.workintech.twitterclone.service;

import com.workintech.twitterclone.entity.Like;

public interface LikeService {

    public void addLike(Long tweetId, Long userId);
    public void removeLike(Long tweetId, Long userId);
}
