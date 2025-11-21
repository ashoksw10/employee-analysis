package com.company.util;

import com.company.model.Employee;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CsvReader {

    public static List<Employee> readEmployees(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] t = line.split(",");

            int id = Integer.parseInt(t[0].trim());
            String name = t[1].trim();
            double salary = Double.parseDouble(t[2].trim());

            Integer managerId = t.length > 3 && !t[3].trim().isEmpty()
                    ? Integer.parseInt(t[3].trim())
                    : null;

            employees.add(new Employee(id, name, salary, managerId));
        }

        return employees;
    }
}
