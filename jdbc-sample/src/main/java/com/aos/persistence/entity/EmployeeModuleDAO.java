package com.aos.persistence.entity;

import java.sql.Connection;
import java.sql.SQLException;

import com.aos.persistence.ConnectionUtil;

public class EmployeeModuleDAO {
  private Connection connection;

  public EmployeeModuleDAO() {
    try {
      Connection connetion = ConnectionUtil.getConnection();
      this.connection = connetion;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insert(final long employeeId, final long moduleId) {
    try {
      String sql = "INSERT INTO employees_modules(employee_id, module_id) VALUES(?, ?)";
      var statement = connection.prepareStatement(sql);
      statement.setLong(1, employeeId);
      statement.setLong(2, moduleId);
      statement.executeUpdate();

      System.out.printf("Foram afetados %s registros no DB", statement.getUpdateCount());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
