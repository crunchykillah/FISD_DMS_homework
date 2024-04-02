package com.technokratos.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Client extends UserEntity {
    @Id
    @SequenceGenerator(name = "client_gen", sequenceName = "client_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "client_gen")
    private Long id;
    @OneToMany(mappedBy = "client")
    private List<Product> products;
    private String Address;
}
