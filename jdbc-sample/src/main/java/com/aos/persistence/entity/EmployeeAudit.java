package com.aos.persistence.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record EmployeeAudit(
    long id,
    long employeeId,
    String name,
    String oldName,
    BigDecimal salary,
    BigDecimal oldSalary,
    OffsetDateTime birthday,
    OffsetDateTime oldBirthday,
    OperationEnum operation) {

}
