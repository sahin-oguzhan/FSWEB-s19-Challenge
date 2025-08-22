package com.workintech.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workintech.twitterclone.exceptions.TwitterException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "twitterclone")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String username;

    @Column(name = "first_name")
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
    private LocalDate registrationDate = LocalDate.now();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", schema = "twitterclone", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Tweet> tweets = new ArrayList<>();

    public void addTweet(Tweet tweet) {
        if (tweet == null)
            throw new TwitterException("Tweet cannot be null", HttpStatus.BAD_REQUEST);

        if (!tweets.contains(tweet)) {
            tweet.setUser(this);
            tweets.add(tweet);
        }
    }

    public void removeTweet(Tweet tweet){
        tweets.remove(tweet);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public void addLike(Like like){
        if (!likes.contains(like)){
            like.setUser(this);
            likes.add(like);
        }
    }

    public void removeLike(Like like){
        likes.remove(like);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        if (comment == null)
         throw new TwitterException("Comment cannot be null!", HttpStatus.BAD_REQUEST);

        if (!comments.contains(comment)){
            comment.setUser(this);
            comments.add(comment);
        }
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Retweet> retweets = new ArrayList<>();

    public void addRetweet(Retweet retweet){
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
