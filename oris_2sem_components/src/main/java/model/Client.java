package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Client {
    private UUID clientId;
    private String username;
    private String password;
    private Basket basket;
}
