CREATE OR REPLACE TRIGGER BRG_EOTH_{id}_TRG
{event} {action}
ON {attribute_table}
FOR EACH ROW
DECLARE
  L_PASSED      BOOLEAN := TRUE;
BEGIN
    {sql_code};
    IF NOT l_PASSED
    THEN
    RAISE_APPLICATION_ERROR(-20000, {error});
    END IF;
END;