package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.dto.tweet.TweetResponseDto;
import com.workintech.twitterclone.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long> {
    @Query("SELECT t FROM Tweet t WHERE t.user.username = :username")
    public List<Tweet> findByUsername(String username);

    @Query("SELECT t FROM Tweet t WHERE t.user.id = :userId")
    public List<Tweet> findByUserId(Long userId);
}
