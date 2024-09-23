CREATE TABLE employees_modules(
  employee_id BIGINT NOT NULL,
  module_id BIGINT NOT NULL,

  PRIMARY KEY(employee_id, module_id),

  CONSTRAINT fk_employees_modules_employee FOREIGN KEY(employee_id) REFERENCES employees(id),
  CONSTRAINT fk_employees_modules_module FOREIGN KEY(module_id) REFERENCES modules(id)
);
