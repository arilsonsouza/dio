package com.aos.persistence.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Employee {

  private long id;

  private String name;

  private BigDecimal salary;

  private OffsetDateTime birthday;

  private List<Contact> contacts = new ArrayList<>();

  private List<Module> modules = new ArrayList<>();

  public void addContact(Contact contact) {
    this.contacts.add(contact);
  }

  public void addModule(Module module) {
    this.modules.add(module);
  }
}
