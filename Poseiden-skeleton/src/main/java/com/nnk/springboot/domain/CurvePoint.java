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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id")
    private Integer id;

    @Column(name="_curveId")
    private Integer curveId;

    @Column(name="_asOfDate")
    private Timestamp asOfDate;

    @Column(name="_term")
    private Double term;

    @Column(name="_value")
    private Double value;

    @Column(name="_creationDate")
    private Timestamp creationDate;

}
