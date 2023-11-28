package com.kamil.courseerpbackend.service.employee;

import com.kamil.courseerpbackend.model.entity.Employee;
import com.kamil.courseerpbackend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    @Override
    public void insert(Employee employee) {
        employeeRepository.save(employee);
    }
}
