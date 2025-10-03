package org.example.exam2.repository;

import org.example.exam2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(Employee.Status status);
    List<Employee> findByCodeContaining(String code);
    List<Employee> findByPosition(String position);
    List<Employee> findByOnboardDateBetween(LocalDate start, LocalDate end);

}