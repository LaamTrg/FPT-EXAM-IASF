package org.example.exam2.service;

import org.example.exam2.entity.Employee;
import org.example.exam2.entity.Salary;
import org.example.exam2.repository.EmployeeRepository;
import org.example.exam2.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    public Employee addEmployee(Employee employee, Salary salary) {
        salary.calculate();
        employee.setSalary(salary);
        salary.setEmployee(employee);
        return employeeRepository.save(employee);
    }

    public List<Employee> getWorkingEmployees() {
        return employeeRepository.findByStatus(Employee.Status.WORKING);
    }

    public List<Employee> searchByCode(String code) {
        return employeeRepository.findByCodeContaining(code);
    }

    public Employee getEmployeeDetail(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public double calculateTotalCost() {
        List<Employee> employees = employeeRepository.findByStatus(Employee.Status.WORKING);
        return employees.stream().mapToDouble(emp -> {
            Salary s = emp.getSalary();
            return s.getNetSalary() + (2 * s.getInsurance()) + s.getAllowance();
        }).sum();
    }

    public List<Employee> getByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    public List<Employee> getByOnboardDateRange(LocalDate start, LocalDate end) {
        return employeeRepository.findByOnboardDateBetween(start, end);
    }

}