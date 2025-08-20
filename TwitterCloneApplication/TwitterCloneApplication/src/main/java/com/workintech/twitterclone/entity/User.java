package com.workintech.twitterclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "twitterclone")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String username;

    @Column(name = "user_name")
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    private String password;

    @Column(name = "registration_date")
    @NotNull
    @NotEmpty
    @NotBlank
    private LocalDate registrationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Tweet> tweets = new ArrayList<>();

    public void addTweet(Tweet tweet) {
        //TODO if (tweet == null)
            //TODO throw new IllegalArgumentException("");

        if (!tweets.contains(tweet)) {
            tweet.setUser(this);
            tweets.add(tweet);
        }
    }

    public void removeTweet(Tweet tweet){
        tweets.remove(tweet);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public void addLike(Like like){
        //TODO if (like == null)
        //TODO throw new IllegalArgumentException("");

        if (!likes.contains(like)){
            like.setUser(this);
            likes.add(like);
        }
    }

    public void removeLike(Like like){
        likes.remove(like);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        //TODO if (comment == null)
        //TODO throw new IllegalArgumentException("");

        if (!comments.contains(comment)){
            comment.setUser(this);
            comments.add(comment);
        }
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Retweet> retweets = new ArrayList<>();

    public void addRetweet(Retweet retweet){
        //TODO if (retweet == null)
        //TODO throw new IllegalArgumentException("");

        if (!retweets.contains(retweet)) {
            retweet.setUser(this);
            retweets.add(retweet);
        }
    }

    public void removeRetweet(Retweet retweet){
        retweets.remove(retweet);
    }

    public List<Tweet> getTweets() {
        return Collections.unmodifiableList(this.tweets);
    }

    public List<Like> getLikes() {
        return Collections.unmodifiableList(this.likes);
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(this.comments);
    }

    public List<Retweet> getRetweets() {
        return Collections.unmodifiableList(this.retweets);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != getClass())
            return false;

        User user = (User) obj;
        return user.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
