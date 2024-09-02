package com.aos.persistence.entity;

import lombok.Data;

@Data
public class Contact {

  private long id;

  private String name;

  private String type;

  private long employeeId;
}
