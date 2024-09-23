CREATE TABLE contacts(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  type VARCHAR(50) NOT NULL,
  employee_id BIGINT NOT NULL,

  CONSTRAINT fk_contacts_employee FOREIGN KEY(employee_id) REFERENCES employees(id)
);
