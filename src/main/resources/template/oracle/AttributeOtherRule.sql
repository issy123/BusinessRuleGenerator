CREATE OR REPLACE TRIGGER BRG_AOTH_{table_name}_{id}_TRG
{event} {action}
ON {attribute_table}
FOR EACH ROW
DECLARE
    l_passed      BOOLEAN := TRUE;
BEGIN
    l_passed := {sql_code};
    IF NOT l_passed
    THEN
    RAISE_APPLICATION_ERROR(-20000, '{error_message}');
    END IF;
END;