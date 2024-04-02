package com.technokratos.model;

import com.technokratos.annotation.Entity;
import com.technokratos.annotation.Id;
import com.technokratos.annotation.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Author {
    @Id
    private Integer id;
    private String name;
    @ManyToOne
    private Country country;
}
