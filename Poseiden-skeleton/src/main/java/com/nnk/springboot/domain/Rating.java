package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rating")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "moodys_rating")
    private String moodysRating;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "sandp_rating")
    private String sandPRating;

    @NotBlank(message = "This field is mandatory")
    @Column(name = "fitch_rating")
    private String fitchRating;

    @NotNull(message = "This field is mandatory")
    @Column(name = "order_number")
    private Integer orderNumber;
}
