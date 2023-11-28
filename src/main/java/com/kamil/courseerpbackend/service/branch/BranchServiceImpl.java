package com.kamil.courseerpbackend.service.branch;

import com.kamil.courseerpbackend.model.entity.Branch;
import com.kamil.courseerpbackend.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService{
    private final BranchRepository branchRepository;
    @Override
    public void insert(Branch branch) {
        branchRepository.save(branch);
    }
}
