package com.aos.persistence.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.aos.persistence.ConnectionUtil;

public class EmployeeAuditDAO {
  private Connection connection;

  public EmployeeAuditDAO() {
    try {
      Connection connetion = ConnectionUtil.getConnection();
      this.connection = connetion;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<EmployeeAudit> findAll() {
    List<EmployeeAudit> employees = new ArrayList<>();
    try {
      String sql = "SELECT * FROM view_employee_audit";
      var statement = connection.prepareStatement(sql);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {

        EmployeeAudit employee = new EmployeeAudit(
            rs.getLong("id"),
            rs.getLong("employee_id"),
            rs.getString("name"),
            rs.getString("old_name"),
            rs.getBigDecimal("salary"),
            rs.getBigDecimal("old_salary"),
            getDateTimeOrNull(rs, "birthday"),
            getDateTimeOrNull(rs, "old_birthday"),
            OperationEnum.getByDbOperation(rs.getString("operation")));

        employees.add(employee);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return employees;
  }

  private OffsetDateTime getDateTimeOrNull(ResultSet rs, String field) throws SQLException {
    return rs.getTimestamp(field) == null ? null
        : OffsetDateTime.ofInstant(rs.getTimestamp(field).toInstant(), ZoneOffset.UTC);
  }

}
