package com.boot.lions.repos;

import com.boot.lions.domain.Role;
import com.boot.lions.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
