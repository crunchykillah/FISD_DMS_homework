package model;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Author {
    private String name;
    private String surname;
    private String country;
}
