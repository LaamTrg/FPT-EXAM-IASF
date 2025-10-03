package org.example.exam2.controller;

import org.example.exam2.entity.Employee;
import org.example.exam2.entity.Salary;
import org.example.exam2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        Salary salary = employee.getSalary();
        return employeeService.addEmployee(employee, salary);
    }

    @GetMapping("/working")
    public List<Employee> getWorkingEmployees() {
        return employeeService.getWorkingEmployees();
    }

    @GetMapping("/search")
    public List<Employee> searchByCode(@RequestParam String code) {
        return employeeService.searchByCode(code);
    }

    @GetMapping("/{id}")
    public Employee getDetail(@PathVariable Long id) {
        return employeeService.getEmployeeDetail(id);
    }

    @GetMapping("/total-cost")
    public double getTotalCost() {
        return employeeService.calculateTotalCost();
    }

    @GetMapping("/by-position")
    public List<Employee> getByPosition(@RequestParam String position) {
        return employeeService.getByPosition(position);
    }

    @GetMapping("/by-date")
    public List<Employee> getByDateRange(@RequestParam String start, @RequestParam String end) {
        return employeeService.getByOnboardDateRange(LocalDate.parse(start), LocalDate.parse(end));
    }

}