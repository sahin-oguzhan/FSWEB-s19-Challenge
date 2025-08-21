package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query("SELECT l FROM Like l WHERE l.user.id = :userId AND l.tweet.id = :tweetId")
    Optional<Like> isLikedByUserIdAndTweetId(Long tweetId, Long userId);
}
