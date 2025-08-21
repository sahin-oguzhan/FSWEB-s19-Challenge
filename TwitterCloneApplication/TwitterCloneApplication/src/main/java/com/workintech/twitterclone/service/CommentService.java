package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.comment.CommentRequestDto;
import com.workintech.twitterclone.dto.comment.CommentResponseDto;

import java.util.List;

public interface CommentService {
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long tweetId, Long userId);
    public CommentResponseDto deleteComment(Long commentId);
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto);
    public CommentResponseDto findById(Long id);
    public List<CommentResponseDto> findAll();
}
