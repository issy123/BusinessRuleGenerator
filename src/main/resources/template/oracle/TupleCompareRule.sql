CREATE OR REPLACE TRIGGER BRG_TCMP_{table_name}_TRG
    BEFORE INSERT OR UPDATE
    ON {table_name}
    FOR EACH ROW
DECLARE
    l_passed       BOOLEAN := FALSE;
    v_column1      {column_type} := :NEW.{column_name};
    v_value        {column_type2} := :NEW.{column_name2};
BEGIN
    IF NOT l_passed THEN
        IF (v_column1 {comparison} v_value) THEN
            l_passed := TRUE;
        ELSE
            RAISE_APPLICATION_ERROR(-20000, '{error_message}');
        END IF;
    END IF;
END;