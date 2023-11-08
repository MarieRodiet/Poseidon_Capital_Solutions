package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rule_name")
@Getter
@Setter
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "json")
    private String json;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "template")
    private String template;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "sql_str")
    private String sqlStr;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "sql_part")
    private String sqlPart;
}
