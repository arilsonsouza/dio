package com.aos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;

import org.flywaydb.core.Flyway;

import com.aos.persistence.entity.Employee;
import com.aos.persistence.entity.EmployeeAuditDAO;
import com.aos.persistence.entity.EmployeeDAO;

import net.datafaker.Faker;

/**
 * Hello world!
 *
 */
public class App {
    private final static EmployeeDAO employeeDAO = new EmployeeDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
    private final static Faker faker = new Faker(Locale.getDefault());

    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/jdbc_sample", "postgres", "postgres")
                .load();

        flyway.migrate();

        // Employee employee = new Employee();
        // employee.setName("Arilson");
        // employee.setSalary(new BigDecimal("100000"));
        // employee.setBirthday(OffsetDateTime.now().minusYears(20));

        // employeeDAO.insert(employee);

        // System.out.println("LIST ALL");
        // employeeDAO.findAll().forEach(System.out::println);

        // System.out.println("FIND BY ID");
        // System.out.println(employeeDAO.findById(1L));

        // System.out.println("UPDATE");
        // employee.setId(1);
        // employee.setName("João");
        // employee.setSalary(new BigDecimal("12563"));
        // employee.setBirthday(OffsetDateTime.now().minusYears(28));
        // employeeDAO.update(employee);

        // System.out.println("DELETE");
        // employeeDAO.delete(1);

        // System.out.println("LIST EMPLOYEE AUDIT LOGS VIEW");
        // employeeAuditDAO.findAll().forEach(System.out::println);

        // System.out.println("INSERT WITH PROCEDURE");
        // employee.setName("PROCEDURE USER");
        // employeeDAO.insertWithProcedure(employee);

        var employees = Stream.generate(() -> {
            var e = new Employee();
            e.setName(faker.name().fullName());
            e.setSalary(new BigDecimal(faker.number().digits(4)));
            e.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(20, 40)),
                    LocalTime.MIN, ZoneOffset.UTC));
            return e;
        }).limit(10000).toList();

        employeeDAO.insertBatch(employees);

    }
}
