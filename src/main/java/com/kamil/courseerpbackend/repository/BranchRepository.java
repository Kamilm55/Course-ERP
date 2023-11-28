package com.kamil.courseerpbackend.repository;

import com.kamil.courseerpbackend.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch,Long> {
}
