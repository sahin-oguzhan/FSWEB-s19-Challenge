package com.workintech.twitterclone.Repository;

import com.workintech.twitterclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
