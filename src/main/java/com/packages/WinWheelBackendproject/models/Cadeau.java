package com.packages.WinWheelBackendproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table
public class Cadeau implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @SequenceGenerator(
            name = "cadeau_sequence",
            sequenceName = "cadeau_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cadeau_sequence")
    private Long id;
    private String nom;
    private String description;
    private Integer stock;
    @JsonIgnore
    private LocalDate dateDernierModification;
    @ManyToOne()
    @JsonIgnore
    private Utilisateur admin;
    @JsonIgnore
    @ManyToOne
    private Selection selection;

    public Cadeau(@JsonProperty("nom") String nom, @JsonProperty("description") String description, @JsonProperty("stock") Integer stock) {
        this.nom = nom;
        this.description = description;
        this.stock = stock;
        this.dateDernierModification = LocalDate.now();
    }
}
