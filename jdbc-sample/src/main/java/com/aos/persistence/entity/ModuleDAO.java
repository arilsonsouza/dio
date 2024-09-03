package com.aos.persistence.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aos.persistence.ConnectionUtil;

public class ModuleDAO {
  private Connection connection;

  public ModuleDAO() {
    try {
      Connection connetion = ConnectionUtil.getConnection();
      this.connection = connetion;
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Module> findAll() {
    List<Module> modules = new ArrayList<>();
    try {
      String sql = "SELECT \n" + //
          "   m.id AS module_id, \n" + //
          "   m.name AS module_name, \n" + //
          "   e.id AS employee_id,\n" + //
          "   e.name AS employee_name\n" + //
          "FROM modules m\n" + //
          "JOIN employees_modules em ON em.module_id = m.id\n" + //
          "JOIN employees e ON e.id = em.employee_id ORDER BY m.id";
      var statement = connection.prepareStatement(sql);
      ResultSet rs = statement.executeQuery();
      var hasNext = rs.next();
      while (hasNext) {
        Module module = new Module();
        module.setId(rs.getLong("module_id"));
        module.setName(rs.getString("module_name"));
        do {
          Employee employee = new Employee();
          employee.setId(rs.getLong("employee_id"));
          employee.setName(rs.getString("employee_name"));
          module.addEmployee(employee);

          hasNext = rs.next();
        } while (hasNext && (module.getId() == rs.getLong("module_id")));
        modules.add(module);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return modules;
  }
}
