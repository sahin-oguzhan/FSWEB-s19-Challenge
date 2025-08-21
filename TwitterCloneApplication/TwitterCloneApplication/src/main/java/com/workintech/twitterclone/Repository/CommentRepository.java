package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT c FROM Comment c WHERE c.tweet.id = :tweetId")
    List<Comment> findAllCommentsByTweetId(Long tweetId);
}
