package com.example.demo.model.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

    @Query("SELECT d FROM data_table d WHERE d.UUID=?1")
    Optional<Data> findDatabyUUID(UUID uuid);
}
