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
    @Column
    private Integer id;

    @Column
    private String moodysRating;

    @Column
    private String sandPRating;

    @Column
    private String fitchRating;

    @Column
    private Integer orderNumber;
}
