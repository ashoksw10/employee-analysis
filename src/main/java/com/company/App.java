package com.company;

import com.company.model.Employee;
import com.company.service.EmployeeService;
import com.company.service.HierarchyService;
import com.company.util.CsvReader;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String fileToRead = "src/main/resources/employees.csv";

        List<Employee> employees = CsvReader.readEmployees(fileToRead);

        EmployeeService employeeService = new EmployeeService(employees);
        HierarchyService hierarchyService = new HierarchyService(employeeService);

        System.out.println("===== SALARY VALIDATION =====");

        for (Employee manager : employeeService.getEmployees().values()) {

            var subs = employeeService.getDirectSubordinates(manager.getId());
            if (subs.isEmpty()) continue;

            double avg = subs.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            double minAllowed = avg * 1.20;
            double maxAllowed = avg * 1.50;

            if (manager.getSalary() < minAllowed) {
                System.out.println(manager.getName() + " UNDERPAID by " +
                        (minAllowed - manager.getSalary()));
            } else if (manager.getSalary() > maxAllowed) {
                System.out.println(manager.getName() + " OVERPAID by " +
                        (manager.getSalary() - maxAllowed));
            }
        }

        System.out.println("\n===== REPORTING LINE ISSUES =====");

        for (Employee e : employeeService.getEmployees().values()) {
            int depth = hierarchyService.countManagersToCEO(e);

            if (depth > 4) {
                System.out.println(e.getName() + " has long reporting line: "
                        + depth + " levels (" + (depth - 4) + " too many)");
            }
        }
    }
}
