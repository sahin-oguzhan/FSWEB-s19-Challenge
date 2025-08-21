package com.workintech.twitterclone.controller;

import com.workintech.twitterclone.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    @Autowired
    public final LikeService likeService;

    @PostMapping("/{tweetId}/{userId}")
    public void tweetLike(@PathVariable Long tweetId,@PathVariable Long userId){
        likeService.addLike(tweetId, userId);
    }

    @DeleteMapping("/{tweetId}/{userId}")
        public void unlikeTweet(@PathVariable Long tweetId,@PathVariable Long userId){
        likeService.removeLike(tweetId, userId);
    }

}
