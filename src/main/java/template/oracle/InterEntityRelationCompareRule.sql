CREATE OR REPLACE TRIGGER BRG_ICMP_{table_name}_TRG
    BEFORE INSERT OR UPDATE
    ON {table_name}
    FOR EACH ROW
DECLARE
  l_passed BOOLEAN := TRUE;

  v_column1 {column_type} := :NEW.{interentity_column_1};

  v_column2 {column_type2};

BEGIN
    SELECT {column_name2}
    INTO v_column2
    FROM {table_name2}
    WHERE {table_name}.{column_name3} = {table_name2}.{column_name4};

    IF (v_column1 {comparison} v_column2) THEN
        l_passed := TRUE;
    ELSE
        RAISE_APPLICATION_ERROR(-20000, {error});
    END IF;
END;