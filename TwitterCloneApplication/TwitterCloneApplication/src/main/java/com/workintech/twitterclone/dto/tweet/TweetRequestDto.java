package com.workintech.twitterclone.dto.tweet;

import com.workintech.twitterclone.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TweetRequestDto (@NotNull
                               @NotEmpty
                               @NotBlank
                               String content,
                               Long userId){
}
