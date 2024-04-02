package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class Department {
    public UUID departmentId;
    public String phoneNumber;
}
