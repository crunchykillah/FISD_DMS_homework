package ru.itis.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
@RequiredArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "product_gen", sequenceName = "product_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "product_gen")
    private Long product_id;
    private String name;
    private int weight;
}
