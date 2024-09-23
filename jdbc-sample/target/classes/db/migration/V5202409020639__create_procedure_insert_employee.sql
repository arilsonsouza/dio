CREATE OR REPLACE PROCEDURE prc_insert_employee(
  IN name VARCHAR,
  IN salary DECIMAL,
  IN birthday TIMESTAMP
)
LANGUAGE 'plpgsql'
AS $$
BEGIN
  INSERT INTO employees(name, salary, birthday) VALUES(name, salary, birthday);
END;
$$;
