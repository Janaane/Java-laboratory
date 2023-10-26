package org.example;

public class Employee {
    private String name;
    private int age;
    private String gender;
    private int salary;
    public Employee(String name, int age, String gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public int getAge() {
        return age;
    }
}
