CREATE VIEW view_employee_audit AS
  SELECT
    id,
    employee_id,
    name,
    old_name,
    salary,
    old_salary,
    birthday,
    old_birthday,
    operation,
    created_at
  FROM employees_audit
  ORDER BY created_at;
