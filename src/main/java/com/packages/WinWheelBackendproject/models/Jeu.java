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
public class Jeu implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String description;
    private LocalDate dateCreation;
    @ManyToOne
    @JsonIgnore
    private Utilisateur admin;

    public Jeu(@JsonProperty("nom") String nom, @JsonProperty("description") String description) {
        this.nom = nom;
        this.description = description;
        this.dateCreation = LocalDate.now();
    }
}
