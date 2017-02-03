create trigger BRG_ACMP_{id}_{upper_event}_TRG
before {event} on {table_name}
for each row
begin
  if(new.agency < 1 or new.agency >5) then
    SIGNAL 'your error message'
  end if 
end

create trigger BRG_ACMP_{id}_{upper_event}_TRG
before update on {table_name}
for each row
begin
    DECLARE ComparisonValue {column_type};
    SET @ComparisonValue := {value};
    IF (new.{column_name} {comparison} @ComparisonValue) THEN
        l_passed := TRUE;
    ELSE
        RAISE_APPLICATION_ERROR(-20000, '{error_message}');
    END IF
  if(new.agency < 1 or new.agency >5) then
    SIGNAL 'your error message'
  end if 
end