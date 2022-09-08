package com.example.PP;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;

@Repository
public interface League_TableRepository extends JpaRepository<League_Table, Integer> {
}
