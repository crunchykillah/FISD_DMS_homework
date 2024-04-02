package com.technokratos.model;

import com.technokratos.annotation.Entity;
import com.technokratos.annotation.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Country {

    @Id
    private Integer id;
    private String name;
}
