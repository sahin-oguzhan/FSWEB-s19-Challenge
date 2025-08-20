package com.workintech.twitterclone.Repository;

import com.workintech.twitterclone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
