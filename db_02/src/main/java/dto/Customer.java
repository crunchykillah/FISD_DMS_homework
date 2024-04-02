package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter@Getter
public class Customer {
    public UUID customerId;
    public String nameOfCustomer;
    public String surnameOfCustomer;
    public String phoneNumber;
}
