package com.example.employee_management.model;

import java.time.LocalDate;

public class Employee {
    protected int id;
    protected String name;
    protected String position;
    protected String department;
    protected LocalDate hire_date;

    public Employee(){}

    public Employee(int id, String name, String position, String department) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public Employee(String name, String position, String department, LocalDate hire_date) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.hire_date = hire_date;
    }

    public Employee(int id, String name, String position, String department, LocalDate hire_date) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.hire_date = hire_date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
}
