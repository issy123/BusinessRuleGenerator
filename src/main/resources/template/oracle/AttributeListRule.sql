CREATE OR REPLACE TRIGGER BRG_ALIS_{id}_TRG
BEFORE DELETE OR INSERT OR UPDATE
ON {table_name}
FOR EACH ROW
DECLARE
  l_passed BOOLEAN := FALSE;
BEGIN
    IF '{list_type}' = 'INC' THEN
      IF :NEW.{column_name} IN ({list_items}) THEN
        l_passed := TRUE;
      ELSE
        RAISE_APPLICATION_ERROR(-20000, '{error_message}');
      END IF;
    ELSE
      IF :NEW.{column_name} NOT IN ({list_items}) THEN
        l_passed := TRUE;
      ELSE
        RAISE_APPLICATION_ERROR(-20000, '{error_message}');
      END IF;
    END IF;

  IF NOT L_PASSED THEN
    RAISE_APPLICATION_ERROR(-20000, '{error_message}' );
    END IF;
  END;