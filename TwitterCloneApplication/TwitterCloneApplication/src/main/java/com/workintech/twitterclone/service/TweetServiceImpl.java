package com.workintech.twitterclone.service;

import com.workintech.twitterclone.dto.tweet.TweetRequestDto;
import com.workintech.twitterclone.dto.tweet.TweetResponseDto;
import com.workintech.twitterclone.entity.Tweet;
import com.workintech.twitterclone.entity.User;
import com.workintech.twitterclone.exceptions.TweetNotFoundException;
import com.workintech.twitterclone.exceptions.UserNotFoundException;
import com.workintech.twitterclone.mapper.TweetMapper;
import com.workintech.twitterclone.repository.TweetRepository;
import com.workintech.twitterclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TweetMapper tweetMapper;

    @Override
    public List<TweetResponseDto> getAll() {
        return tweetRepository.findAll().stream().map(tweetMapper::toResponse).toList();
    }

    @Override
    public List<TweetResponseDto> findByUserName(String username) {
        List<Tweet> tweets = tweetRepository.findByUsername(username);

        if (tweets.isEmpty())
            throw new TweetNotFoundException("Tweet not found with username: " + username);

        return tweets.stream().map(tweetMapper::toResponse).toList();

    }

    @Override
    public TweetResponseDto create(TweetRequestDto tweetRequestDto) {
        Tweet tweet = tweetMapper.toEntity(tweetRequestDto);
        User user = userRepository.findById(tweetRequestDto.userId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + tweetRequestDto.userId()));
        tweet.setUser(user);
        user.addTweet(tweet);
        return tweetMapper.toResponse(tweetRepository.save(tweet));
    }


    @Override
    public TweetResponseDto update(Long id, TweetRequestDto tweetRequestDto) {
        Tweet tweetToUpdate = tweetRepository
                .findById(id)
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found with id: " + id));

        tweetToUpdate.setContent(tweetRequestDto.content());
        return tweetMapper.toResponse(tweetRepository.save(tweetToUpdate));
    }

    @Override
    public void delete(Long id) {
        tweetRepository.deleteById(id);
    }
}
