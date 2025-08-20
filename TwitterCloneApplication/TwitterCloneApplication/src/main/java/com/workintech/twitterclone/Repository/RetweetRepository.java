package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetweetRepository extends JpaRepository<Retweet,Long> {
}
