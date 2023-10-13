package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Getter
@Setter
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private Integer curveId;

    @Column
    private Timestamp asOfDate;

    @Column
    private Double term;

    @Column
    private Double value;

    @Column
    private Timestamp creationDate;

}
