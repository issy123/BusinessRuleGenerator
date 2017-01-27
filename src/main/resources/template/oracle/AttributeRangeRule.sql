CREATE OR REPLACE TRIGGER BRG_ARNG_{table_name}_TRG
    BEFORE INSERT OR UPDATE
    ON {table_name}
    FOR EACH ROW
BEGIN
    DECLARE
        l_passed BOOLEAN := TRUE;
    BEGIN
        IF '{range_type}' = 'INC' THEN
            l_passed := :NEW.{column_name} >= {min} AND :NEW.{column_name} <= {max};
        ELSE
            l_passed := :NEW.{column_name} < {min} OR :NEW.{column_name} > {max};
        END IF;

        IF NOT l_passed THEN
            raise_application_error(-20000, '{error_message}');
        END IF;
    END;
END;