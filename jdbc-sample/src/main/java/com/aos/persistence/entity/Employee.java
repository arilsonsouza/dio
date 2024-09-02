package com.aos.persistence.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Employee {

  private long id;

  private String name;

  private BigDecimal salary;

  private OffsetDateTime birthday;

  private Contact contact;
}
