package com.company.model;

public class Employee {
    private final int id;
    private final String name;
    private final double salary;
    private final Integer managerId;

    public Employee(int id, String name, double salary, Integer managerId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.managerId = managerId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public Integer getManagerId() { return managerId; }

    @Override
    public String toString() {
        return id + " - " + name + " ($" + salary + ")";
    }
}
