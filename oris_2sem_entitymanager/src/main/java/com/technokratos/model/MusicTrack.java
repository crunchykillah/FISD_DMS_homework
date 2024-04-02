package com.technokratos.model;

import com.technokratos.annotation.Entity;
import com.technokratos.annotation.Id;
import com.technokratos.annotation.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class MusicTrack {

    @Id
    private Integer id;
    private String name;
    private Integer length;
    @ManyToOne
    private Musician musician;
    @ManyToOne
    private Author author;
    private String date;
    @ManyToOne
    private Genre genre;
}
