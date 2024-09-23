CREATE OR REPLACE FUNCTION employee_audit_log()
RETURNS trigger AS $BODY$
BEGIN
-- Aqui temos um bloco IF que confirmará o tipo de operação.
IF (TG_OP = 'INSERT') THEN
INSERT INTO employees_audit (employee_id, name, salary, birthday, operation)
VALUES (new.id, new.name, new.salary, new.birthday, 'I');
RETURN NEW;
-- Aqui temos um bloco IF que confirmará o tipo de operação UPDATE.
ELSIF (TG_OP = 'UPDATE') THEN
INSERT INTO employees_audit (employee_id, name, old_name, salary, old_salary, birthday, old_birthday, operation)
VALUES (new.id, new.name, OLD.name, new.salary, OLD.salary, new.birthday, OLD.birthday, 'U');
RETURN NEW;
-- Aqui temos um bloco IF que confirmará o tipo de operação DELETE
ELSIF (TG_OP = 'DELETE') THEN
INSERT INTO employees_audit (employee_id, name, salary, birthday, operation)
VALUES (OLD.id, OLD.name, OLD.salary, OLD.birthday, 'D');
RETURN OLD;
END IF;
RETURN NULL;
END;
$BODY$
LANGUAGE plpgsql VOLATILE
;


CREATE TRIGGER trigger_employees_audit_log
AFTER INSERT OR UPDATE OR DELETE ON employees
FOR EACH ROW
EXECUTE PROCEDURE employee_audit_log();
