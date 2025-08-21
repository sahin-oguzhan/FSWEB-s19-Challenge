package com.workintech.twitterclone.dto.tweet;

import com.workintech.twitterclone.entity.Comment;
import com.workintech.twitterclone.entity.Like;
import com.workintech.twitterclone.entity.Retweet;
import com.workintech.twitterclone.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TweetResponseDto(@NotNull
                               @NotEmpty
                               @NotBlank
                               String content,
                               User user,
                               List<Like> likes,
                               List<Comment> comments,
                               List<Retweet> retweets) {
}
