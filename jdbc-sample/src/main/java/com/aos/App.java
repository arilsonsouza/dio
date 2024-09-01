package com.aos;

import org.flywaydb.core.Flyway;

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
    }
}
