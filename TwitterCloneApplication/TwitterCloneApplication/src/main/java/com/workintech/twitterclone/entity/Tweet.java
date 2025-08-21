package com.workintech.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweet", schema = "twitterclone")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String content;

    @Column(name = "tweet_time")
    private LocalDateTime tweetTime = LocalDateTime.now();

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    private List<Like> likes = new ArrayList<>();

    public void addLike(Like like) {
        //TODO if (like == null)
        //TODO throw new IllegalArgumentException("");

        if (!likes.contains(like)) {
            like.setTweet(this);
            likes.add(like);
        }
    }

    public void removeLike(Like like){
        likes.remove(like);
    }

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        //TODO if (comment == null)
        //TODO throw new IllegalArgumentException("");

        if (!comments.contains(comment)) {
            comment.setTweet(this);
            comments.add(comment);
        }
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Retweet> retweets = new ArrayList<>();

    public void addRetweet(Retweet retweet) {
        //TODO if (retweet == null)
        //TODO throw new IllegalArgumentException("");

        if (!retweets.contains(retweet)) {
            retweet.setTweet(this);
            retweets.add(retweet);
        }
    }

    public void removeRetweet(Retweet retweet){
        retweets.remove(retweet);
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

        Tweet tweet = (Tweet) obj;
        return tweet.getId().equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
