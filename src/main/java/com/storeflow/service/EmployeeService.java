package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Employee;
import com.storeflow.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(int id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setRole(employee.getRole());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setBranchId(employee.getBranchId());
        existingEmployee.setJoiningDate(employee.getJoiningDate());
        existingEmployee.setStatus(employee.getStatus());

        return employeeRepository.save(existingEmployee);
    }

  public String deleteEmployee(int id) {

Employee employee = employeeRepository.findById(id)
.orElseThrow(() -> new RuntimeException("Employee Not Found"));
employee.setStatus("INACTIVE");
employeeRepository.save(employee);
return "Employee Deactivated Successfully";

}
}