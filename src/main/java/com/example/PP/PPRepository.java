package com.example.PP;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PPRepository extends JpaRepository<PP , Integer> {
}
