package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.comment.CommentRequestDto;
import com.workintech.twitterclone.dto.comment.CommentResponseDto;
import com.workintech.twitterclone.entity.Comment;
import com.workintech.twitterclone.entity.Tweet;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.exceptions.CommentNotFoundException;
import com.workintech.twitterclone.exceptions.TweetNotFoundException;
import com.workintech.twitterclone.exceptions.UserNotFoundException;
import com.workintech.twitterclone.repository.CommentRepository;
import com.workintech.twitterclone.repository.TweetRepository;
import com.workintech.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final TweetRepository tweetRepository;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long tweetId, Long userId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet not found with id: " + tweetId));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        Comment comment = new Comment();
        comment.setComment(commentRequestDto.comment());

        comment.setTweet(tweet);
        comment.setUser(user);

        user.addComment(comment);
        tweet.addComment(comment);

        commentRepository.save(comment);

        return new CommentResponseDto(comment.getComment());
    }

    @Override
    public CommentResponseDto deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        comment.getUser().removeComment(comment);
        comment.getTweet().removeComment(comment);

        commentRepository.delete(comment);

        return new CommentResponseDto(comment.getComment());
    }

    @Override
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        comment.setComment(commentRequestDto.comment());
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getComment());
    }

    @Override
    public CommentResponseDto findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));

        return new CommentResponseDto(comment.getComment());
    }

    @Override
    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll().stream().map(c -> new CommentResponseDto(c.getComment())).toList();
    }

    public List<CommentResponseDto> findAllCommentsByTweetId(Long tweetId){
        return commentRepository.findAllCommentsByTweetId(tweetId).stream().map(c -> new CommentResponseDto(c.getComment())).toList();
    }
}
