package com.kamil.courseerpbackend.repository;

import com.kamil.courseerpbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}