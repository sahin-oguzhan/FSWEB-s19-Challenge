package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet,Long> {
    @Query("SELECT r FROM Retweet r WHERE r.user.id = :userId AND r.tweet.id = :tweetId")
    Optional<Retweet> findByUserIdAndTweetId(Long userId, Long tweetId);
}
