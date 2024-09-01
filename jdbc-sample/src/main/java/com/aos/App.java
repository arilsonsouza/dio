package com.aos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.flywaydb.core.Flyway;

import com.aos.persistence.entity.Employee;
import com.aos.persistence.entity.EmployeeDAO;

/**
 * Hello world!
 *
 */
public class App {
    private final static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/jdbc_sample", "postgres", "postgres")
                .load();

        flyway.migrate();

        Employee employee = new Employee();
        employee.setName("Arilson");
        employee.setSalary(new BigDecimal("100000"));
        employee.setBirthday(OffsetDateTime.now().minusYears(20));

        employeeDAO.insert(employee);

        System.out.println("LIST ALL");
        employeeDAO.findAll().forEach(System.out::println);

        System.out.println("FIND BY ID");
        System.out.println(employeeDAO.findById(1L));

    }
}
