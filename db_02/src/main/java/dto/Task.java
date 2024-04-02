package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class Task {
    public UUID taskId;
    public Employee employee;
    public Project project;
}
