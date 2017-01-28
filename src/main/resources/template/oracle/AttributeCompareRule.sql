CREATE OR REPLACE TRIGGER BRG_ACMP_{table_name}_TRG
    BEFORE INSERT OR UPDATE
    ON {table_name}
    FOR EACH ROW
DECLARE
    l_passed       BOOLEAN := FALSE;
    v_value        {column_type} := {value};
BEGIN
    IF (:NEW.{column_name} {comparison} v_value) THEN
        l_passed := TRUE;
    ELSE
        RAISE_APPLICATION_ERROR(-20000, '{error_message}');
    END IF;
END;