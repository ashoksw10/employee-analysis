package com.company;

import com.company.model.Employee;
import com.company.service.EmployeeService;
import com.company.service.HierarchyService;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HierarchyServiceTest {

    @Test
    public void testCountLevelsToCEO() {
        List<Employee> list = Arrays.asList(
                new Employee(1, "CEO", 300000, null),
                new Employee(2, "Mgr", 150000, 1),
                new Employee(3, "Lead", 90000, 2),
                new Employee(4, "Dev", 60000, 3)
        );

        EmployeeService es = new EmployeeService(list);
        HierarchyService hs = new HierarchyService(es);

        int depth = hs.countManagersToCEO(list.get(3));

        assertEquals(3, depth);
    }
}
