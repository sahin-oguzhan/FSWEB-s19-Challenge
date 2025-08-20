package com.workintech.twitterclone.Repository;

import com.workintech.twitterclone.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetweetRepository extends JpaRepository<Retweet,Long> {
}
