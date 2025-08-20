package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet,Long> {
}
