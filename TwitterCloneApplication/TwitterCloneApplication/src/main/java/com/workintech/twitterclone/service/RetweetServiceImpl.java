package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.retweet.RetweetRequestDto;
import com.workintech.twitterclone.dto.retweet.RetweetResponseDto;
import com.workintech.twitterclone.entity.Retweet;
import com.workintech.twitterclone.entity.Tweet;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.exceptions.RetweetNotFoundException;
import com.workintech.twitterclone.exceptions.TweetNotFoundException;
import com.workintech.twitterclone.exceptions.UserNotFoundException;
import com.workintech.twitterclone.mapper.RetweetMapper;
import com.workintech.twitterclone.mapper.TweetMapper;
import com.workintech.twitterclone.repository.RetweetRepository;
import com.workintech.twitterclone.repository.TweetRepository;
import com.workintech.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService {
    @Autowired
    private final RetweetRepository retweetRepository;
    @Autowired
    private final RetweetMapper retweetMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TweetRepository tweetRepository;

    @Override
    public List<RetweetResponseDto> findAll() {
        return retweetRepository.findAll().stream()
                .map(retweetMapper::toResponse).toList();
    }

    @Override
    public RetweetResponseDto findById(Long id) {
        Retweet retweet = retweetRepository.findById(id).orElseThrow(() -> new RetweetNotFoundException("Retweet not found with id: " + id));
        return retweetMapper.toResponse(retweet);
    }

    @Override
    public RetweetResponseDto createRetweet(RetweetRequestDto retweetRequestDto, Long userId, Long tweetId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet not found with id: " + tweetId));

        Retweet retweet = new Retweet();
        retweet.setUser(user);
        retweet.setTweet(tweet);
        retweet.setContent(retweetRequestDto.content());

        user.addRetweet(retweet);
        tweet.addRetweet(retweet);

        return retweetMapper.toResponse(retweetRepository.save(retweet));
    }

    @Override
    public RetweetResponseDto updateRetweet(Long retweetId, RetweetRequestDto retweetRequestDto) {
        Retweet retweet = retweetRepository.findById(retweetId).orElseThrow(() -> new RetweetNotFoundException("Retweet not found with id: " + retweetId));
        retweet.setContent(retweetRequestDto.content());
        return retweetMapper.toResponse(retweetRepository.save(retweet));
    }

    @Override
    public void deleteRetweet(Long retweetId) {
        retweetRepository.deleteById(retweetId);
    }
}
