package com.aos.persistence.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.aos.persistence.ConnectionUtil;

public class ContactDAO {

  private Connection connection;

  public ContactDAO() {
    try {
      Connection connetion = ConnectionUtil.getConnection();
      this.connection = connetion;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insert(final Contact contact) {
    try {
      String sql = "INSERT INTO contacts(name, type, employee_id) VALUES(?, ?, ?) RETURNING id";
      var statement = connection.prepareStatement(sql);
      statement.setString(1, contact.getName());
      statement.setString(2, contact.getType());
      statement.setLong(3, contact.getEmployeeId());
      ResultSet rs = statement.executeQuery();

      System.out.printf("Foram afetados %s registros no DB", statement.getUpdateCount());
      if (rs.next()) {
        contact.setId(rs.getLong(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
