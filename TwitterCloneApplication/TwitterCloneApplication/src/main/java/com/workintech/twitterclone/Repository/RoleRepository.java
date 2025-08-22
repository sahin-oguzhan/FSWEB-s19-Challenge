package com.workintech.twitterclone.repository;

import com.workintech.twitterclone.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    Role findByAuthority(String authority);
}
