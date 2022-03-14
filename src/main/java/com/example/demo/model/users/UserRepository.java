package com.example.demo.model.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT ud.uuid from users_data ud where ud.username= ?1")
    Optional<User> findUUIDbyUsername(String username);
}
