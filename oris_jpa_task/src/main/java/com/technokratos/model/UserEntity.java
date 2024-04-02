package com.technokratos.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.InheritanceType.SINGLE_TABLE;


@Entity
@Getter@Setter
@Inheritance(strategy = SINGLE_TABLE)
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    private String username;
    private String password;


}
