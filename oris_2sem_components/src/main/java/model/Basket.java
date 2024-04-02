package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Basket {
    private List<Product> products;
}
