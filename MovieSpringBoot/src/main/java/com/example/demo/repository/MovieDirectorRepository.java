package com.example.demo.repository;

import com.example.demo.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDirectorRepository extends JpaRepository<Director, Integer> {

}
