package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
