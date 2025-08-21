package com.workintech.twitterclone.controller;

import com.workintech.twitterclone.dto.comment.CommentRequestDto;
import com.workintech.twitterclone.dto.comment.CommentResponseDto;
import com.workintech.twitterclone.service.CommentService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDto> findAll(){
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public CommentResponseDto findById(@Positive @PathVariable Long id){
        return commentService.findById(id);
    }

    @Transactional
    @PostMapping("/{tweetId}/{userId}")
    public CommentResponseDto addComment(@Validated @RequestBody CommentRequestDto commentRequestDto, @PathVariable Long tweetId, @PathVariable Long userId){
        return commentService.addComment(commentRequestDto, tweetId, userId);
    }

    @Transactional
    @PatchMapping("/{id}")
    public CommentResponseDto updateComment(@Positive @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.updateComment(commentId, commentRequestDto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }

}
