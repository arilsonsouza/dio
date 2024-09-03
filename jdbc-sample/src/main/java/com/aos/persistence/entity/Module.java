package com.aos.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Module {

  private long id;

  private String name;

  private List<Employee> employees = new ArrayList<>();

  public void addEmployee(Employee employee) {
    employees.add(employee);
  }
}
