package com.company;

import com.company.model.Employee;
import com.company.service.EmployeeService;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    @Test
    public void testDirectSubordinates() {
        List<Employee> list = Arrays.asList(
                new Employee(1, "CEO", 300000, null),
                new Employee(2, "Mgr", 150000, 1),
                new Employee(3, "Emp", 80000, 2)
        );

        EmployeeService service = new EmployeeService(list);

        assertEquals(1, service.getDirectSubordinates(1).size());
        assertEquals(1, service.getDirectSubordinates(2).size());
        assertEquals(0, service.getDirectSubordinates(3).size());
    }
}
