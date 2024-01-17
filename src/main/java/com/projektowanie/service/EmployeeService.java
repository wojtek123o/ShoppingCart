package com.projektowanie.service;

import com.projektowanie.exception.EmployeeNotFoundException;
import com.projektowanie.model.Employee;
import com.projektowanie.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        String.format("Employee with id=%s was not found!", id)
                ));
    }
}
