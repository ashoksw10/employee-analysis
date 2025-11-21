package com.company.service;

import com.company.model.Employee;

public class HierarchyService {

    private final EmployeeService employeeService;

    public HierarchyService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public int countManagersToCEO(Employee e) {
        int count = 0;
        Integer managerId = e.getManagerId();

        while (managerId != null) {
            count++;
            managerId = employeeService.getEmployeeById(managerId)
                    .map(Employee::getManagerId)
                    .orElse(null);
        }
        return count;
    }
}
