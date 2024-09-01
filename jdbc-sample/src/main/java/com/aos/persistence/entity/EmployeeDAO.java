package com.aos.persistence.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

  public void update(final Employee employee) {

  }

  public void delete(final long id) {

  }

  public List<Employee> findAll() {
    return null;
  }

  public Employee findById(final long id) {
    return null;
  }

  public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
    return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
  }

}
