package com.packages.WinWheelBackendproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table
public class Essai implements Serializable {
    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue
    private Long id;
    @ToString.Include
    private LocalDate dateDeLaPartie;
    @ManyToOne
    private Utilisateur player;
    @ManyToOne
    private Selection selection;
    @ToString.Include
    private String statut;
    @ToString.Include
    private Long prizeId;

    public Essai(@JsonProperty("statut") String statut) {
        this.statut = statut;
        this.dateDeLaPartie = LocalDate.now();
    }
}
