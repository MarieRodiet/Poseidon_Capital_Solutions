package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "trade")
@Getter
@Setter
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer tradeId;

    @Column
    private String account;

    @Column
    private String type;

    @Column
    private Double buyQuantity;

    @Column
    private Double sellQuantity;

    @Column
    private Double buyPrice;

    @Column
    private Double sellPrice;

    @Column
    private String benchmark;

    @Column
    private Timestamp tradeDate;

    @Column
    private String security;

    @Column
    private String status;

    @Column
    private String trader;

    @Column
    private String book;

    @Column
    private String creationName;

    @Column
    private Timestamp creationDate;

    @Column
    private String revisionName;

    @Column
    private Timestamp revisionDate;

    @Column
    private String dealName;

    @Column
    private String dealType;

    @Column
    private String sourceListId;

    @Column
    private String side;

}
