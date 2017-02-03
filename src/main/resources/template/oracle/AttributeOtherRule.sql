CREATE OR REPLACE TRIGGER BRG_AOTH_{id}_TRG
{event} {action}
ON {attribute_table}
FOR EACH ROW
DECLARE
    l_passed      BOOLEAN := TRUE;
BEGIN
    {sql_code};
    IF NOT l_passed
    THEN
    RAISE_APPLICATION_ERROR(-20000, '{error_message}');
    END IF;
END;