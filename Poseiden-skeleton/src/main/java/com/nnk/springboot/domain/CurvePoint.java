package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "curve_point")
@Getter
@Setter
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @NotNull(message = "This field is mandatory")
    @Column(name = "term")
    private Double term;

    @NotNull(message = "This field is mandatory")
    @Column(name = "curve_value")
    private Double curveValue;

    @Column(name = "creation_date")
    private Timestamp creationDate;

}
