package ru.itis.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_gen")
    @SequenceGenerator(name = "order_item_gen", sequenceName = "order_item_seq")
    private Long id; // Переименовываем поле в id

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id") // Указываем имя столбца для внешнего ключа
    private Order order;

    @ManyToOne
    private Product product;
}