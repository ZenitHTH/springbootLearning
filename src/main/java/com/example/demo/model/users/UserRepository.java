package com.example.demo.model.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT uuid FROM users_data WHERE username= ?1",nativeQuery = true)
    Optional<User> findUUIDbyUsername(String username);
}
