package com.nnk.springboot.domain;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Getter
@Setter
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_bidListId")
    private Integer bidListId;

    @Column(name="_account")
    private String account;

    @Column(name="_type")
    private String type;

    @Column(name="_bidQuantity")
    private Double bidQuantity;

    @Column(name="_askQuantity")
    private Double askQuantity;

    @Column(name="_bid")
    private Double bid;

    @Column(name="_ask")
    private Double ask;

    @Column(name="_benchmark")
    private String benchmark;

    @Column(name="_bidListDate")
    private Timestamp bidListDate;

    @Column(name="_commentary")
    private String commentary;

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
