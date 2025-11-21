package com.company.service;

import com.company.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private final Map<Integer, Employee> employeesById;
    private final Map<Integer, List<Employee>> subordinatesMap;

    public EmployeeService(List<Employee> employees) {
        this.employeesById = employees.stream()
                .collect(Collectors.toMap(Employee::getId, e -> e));

        this.subordinatesMap = new HashMap<>();

        for (Employee e : employees) {
            if (e.getManagerId() != null) {
                subordinatesMap
                        .computeIfAbsent(e.getManagerId(), k -> new ArrayList<>())
                        .add(e);
            }
        }
    }

    public List<Employee> getDirectSubordinates(int managerId) {
        return subordinatesMap.getOrDefault(managerId, Collections.emptyList());
    }

    public Optional<Employee> getEmployeeById(int id) {
        return Optional.ofNullable(employeesById.get(id));
    }

    public Employee getCEO() {
        return employeesById.values().stream()
                .filter(e -> e.getManagerId() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CEO not found"));
    }

    public Map<Integer, Employee> getEmployees() {
        return employeesById;
    }
}
