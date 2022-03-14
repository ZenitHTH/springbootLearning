package com.example.demo.model.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
