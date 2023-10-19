package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rulename")
@Getter
@Setter
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id")
    private Integer id;

    @Column(name="_name")
    private String name;

    @Column(name="_description")
    private String description;

    @Column(name="_json")
    private String json;

    @Column(name="_template")
    private String template;

    @Column(name="_sqlStr")
    private String sqlStr;

    @Column(name="_sqlPart")
    private String sqlPart;

}
