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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String json;
    @Column
    private String template;
    @Column
    private String sqlStr;
    @Column
    private String sqlPart;

}
