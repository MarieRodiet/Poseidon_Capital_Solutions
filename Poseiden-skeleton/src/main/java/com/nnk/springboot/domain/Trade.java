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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_tradeId")
    private Integer tradeId;

    @Column(name="_account")
    private String account;

    @Column(name="_type")
    private String type;

    @Column(name="_buyQuantity")
    private Double buyQuantity;

    @Column(name="_sellQuantity")
    private Double sellQuantity;

    @Column(name="_buyPrice")
    private Double buyPrice;

    @Column(name="_sellPrice")
    private Double sellPrice;

    @Column(name="_benchmark")
    private String benchmark;

    @Column(name="_tradeDate")
    private Timestamp tradeDate;

    @Column(name="_security")
    private String security;

    @Column(name="_status")
    private String status;

    @Column(name="_trader")
    private String trader;

    @Column(name="_book")
    private String book;

    @Column(name="_creationName")
    private String creationName;

    @Column(name="_creationDate")
    private Timestamp creationDate;

    @Column(name="_revisionName")
    private String revisionName;

    @Column(name="_revisionDate")
    private Timestamp revisionDate;

    @Column(name="_dealName")
    private String dealName;

    @Column(name="_dealType")
    private String dealType;

    @Column(name="_sourceListId")
    private String sourceListId;

    @Column(name="_side")
    private String side;

}
