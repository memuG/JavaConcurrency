package com.przemo.concurrency.parallelstreams;

import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person>{
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int salary;
    private double coefficient;

    public Person(int id, String firstName, String lastName, Date birthDate, int salary, double coefficient) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.coefficient = coefficient;
    }


    @Override
    public int compareTo(Person o) {
        int compareLastNames = this.getLastName().compareTo(o.getLastName());
        if (compareLastNames == 0)
            return this.getFirstName().compareTo(o.getLastName());
        return compareLastNames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", coefficient=" + coefficient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
