package com.example.servicesdtosspringbootles12.repository;

import com.example.servicesdtosspringbootles12.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelevisionRepository extends JpaRepository<Television, Long> {

    Optional<Television> findByBrandAndName(String brand, String name);
}
