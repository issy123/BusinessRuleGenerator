CREATE OR REPLACE TRIGGER BRG_ALIS_{table_name}_{id}_TRG
BEFORE DELETE OR INSERT OR UPDATE
ON {table_name}
FOR EACH ROW
DECLARE
  L_PASSED BOOLEAN := FALSE;
BEGIN
    IF '{list_type}' = 'INC' THEN
      IF :NEW.{column_name} IN ({list_items}) THEN
        L_PASSED := TRUE;
      ELSE
        RAISE_APPLICATION_ERROR(-20000, '{error_message}');
      END IF;
    ELSE
      IF :NEW.{column_name} NOT IN ({list_items}) THEN
        L_PASSED := TRUE;
      ELSE
        RAISE_APPLICATION_ERROR(-20000, '{error_message}');
      END IF;
    END IF;

  IF NOT L_PASSED THEN
    RAISE_APPLICATION_ERROR(-20000, '{error_message}' );
    END IF;
  END;