package ru.itis.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
public class Order extends UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
    @SequenceGenerator(name = "order_gen", sequenceName = "order_seq")
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    private Date orderDate;
}