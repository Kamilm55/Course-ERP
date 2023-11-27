package com.kamil.courseerpbackend.service.role;

import com.kamil.courseerpbackend.exception.BaseException;
import com.kamil.courseerpbackend.model.entity.Role;
import com.kamil.courseerpbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final static String OWNER = "OWNER";
    private final RoleRepository roleRepository;

    @Override
    public Role getDefaultRole() {
        return roleRepository.findRoleByName(OWNER).orElseThrow(BaseException::unexpected);
    }

}
