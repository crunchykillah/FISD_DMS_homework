package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class Employee {
    public UUID employeeId;
    public String surname;
    public Department department;
}
