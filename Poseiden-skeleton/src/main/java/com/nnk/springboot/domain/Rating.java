package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rating")
@Setter
@Getter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id")
    private Integer id;

    @Column(name="_moodysRating")
    private String moodysRating;

    @Column(name="_sandPRating")
    private String sandPRating;

    @Column(name="_fitchRating")
    private String fitchRating;

    @Column(name="_orderNumber")
    private Integer orderNumber;
}
