package com.workintech.twitterclone.service;

import com.workintech.twitterclone.entity.Like;
import com.workintech.twitterclone.entity.Tweet;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.exceptions.LikeNotFoundException;
import com.workintech.twitterclone.exceptions.TweetNotFoundException;
import com.workintech.twitterclone.exceptions.TwitterException;
import com.workintech.twitterclone.exceptions.UserNotFoundException;
import com.workintech.twitterclone.repository.LikeRepository;
import com.workintech.twitterclone.repository.TweetRepository;
import com.workintech.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    @Autowired
    private final LikeRepository likeRepository;
    @Autowired
    private final TweetRepository tweetRepository;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public void addLike(Long tweetId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet not found with id: " + tweetId));

        Optional<Like> isLiked = likeRepository.isLikedByUserIdAndTweetId(tweetId, userId);
        if (isLiked.isPresent())
            throw new TwitterException("User alredy liked this tweet", HttpStatus.BAD_REQUEST);

            Like like = new Like();
            like.setUser(user);
            like.setTweet(tweet);

            user.addLike(like);
            tweet.addLike(like);

        likeRepository.save(like);
    }

    @Override
    public void removeLike(Long tweetId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet not found with id: " + tweetId));

        Like like = likeRepository.isLikedByUserIdAndTweetId(tweetId, userId).orElseThrow(() -> new LikeNotFoundException("Tweet is not liked by user: " + user.getUsername()));
        user.removeLike(like);
        tweet.removeLike(like);
        likeRepository.delete(like);
    }


}
