package com.aos.persistence.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.aos.persistence.ConnectionUtil;

public class EmployeeDAO {

  private Connection connection;

  public EmployeeDAO() {
    try {
      Connection connetion = ConnectionUtil.getConnection();
      this.connection = connetion;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insert(final Employee employee) {
    try {
      String sql = "INSERT INTO employees(name, salary, birthday) VALUES(?, ?, ?) RETURNING id";
      var statement = connection.prepareStatement(sql);
      statement.setString(1, employee.getName());
      statement.setBigDecimal(2, employee.getSalary());
      statement.setTimestamp(3, asTimestamp(employee.getBirthday()));
      ResultSet rs = statement.executeQuery();

      System.out.printf("Foram afetados %s registros no DB", statement.getUpdateCount());
      if (rs.next()) {
        employee.setId(rs.getLong(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertBatch(final List<Employee> employees) {
    String sql = "INSERT INTO employees(name, salary, birthday) VALUES(?, ?, ?) RETURNING id";
    try (var statement = connection.prepareStatement(sql)) {
      connection.setAutoCommit(false);
      for (int i = 0; i < employees.size(); i++) {
        statement.setString(1, employees.get(i).getName());
        statement.setBigDecimal(2, employees.get(i).getSalary());
        statement.setTimestamp(3, asTimestamp(employees.get(i).getBirthday()));
        statement.addBatch();
        if (i % 1000 == 0 || i == employees.size() - 1)
          statement.executeBatch();
      }
      connection.commit();
      System.out.printf("Foram afetados %s registros no DB", statement.getUpdateCount());
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
  }

  public void insertWithProcedure(final Employee employee) {
    try {
      String sql = "call prc_insert_employee(?, ?, ?)";
      var statement = connection.prepareStatement(sql);
      statement.setString(1, employee.getName());
      statement.setBigDecimal(2, employee.getSalary());
      statement.setTimestamp(3, asTimestamp(employee.getBirthday()));
      statement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(final Employee employee) {
    try {
      String sql = "UPDATE employees SET name=?, salary=?, birthday=? WHERE id=?";
      var statement = connection.prepareStatement(sql);
      statement.setString(1, employee.getName());
      statement.setBigDecimal(2, employee.getSalary());
      statement.setTimestamp(3, asTimestamp(employee.getBirthday()));
      statement.setLong(4, employee.getId());
      statement.executeUpdate();

      System.out.printf("Foram atualizados %s registros no DB", statement.getUpdateCount());

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(final long id) {
    try {
      String sql = "DELETE FROM employees WHERE id = ?";
      var statement = connection.prepareStatement(sql);
      statement.setLong(1, id);
      statement.executeUpdate();
      System.out.printf("Foram deletados %s registros no DB", statement.getUpdateCount());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Employee> findAll() {
    List<Employee> employees = new ArrayList<>();
    try {
      String sql = "SELECT id, name, salary, birthday FROM employees";
      var statement = connection.prepareStatement(sql);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        employee.setSalary(rs.getBigDecimal("salary"));
        var birthdayInstant = rs.getTimestamp("birthday").toInstant();
        var birthday = OffsetDateTime.ofInstant(birthdayInstant, ZoneOffset.UTC);
        employee.setBirthday(birthday);

        employees.add(employee);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return employees;
  }

  public Employee findById(final long id) {
    Employee employee = new Employee();
    try {
      String sql = "SELECT e.id, e.name, e.salary, e.birthday, c.id AS contact_id, c.name AS contact_name, c.type "
          + "FROM employees e JOIN contacts c ON c.employee_id = e.id WHERE e.id = ?";
      var statement = connection.prepareStatement(sql);
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        Contact contact = new Contact();
        contact.setId(rs.getLong("contact_id"));
        contact.setName(rs.getString("contact_name"));
        contact.setType(rs.getString("type"));

        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        employee.setSalary(rs.getBigDecimal("salary"));
        var birthdayInstant = rs.getTimestamp("birthday").toInstant();
        var birthday = OffsetDateTime.ofInstant(birthdayInstant, ZoneOffset.UTC);
        employee.setBirthday(birthday);
        employee.setContact(contact);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return employee;
  }

  public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
    return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
  }

}
