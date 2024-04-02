package com.technokratos.model;

import com.technokratos.annotation.Entity;
import com.technokratos.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    private Integer id;
    private String name;
}
