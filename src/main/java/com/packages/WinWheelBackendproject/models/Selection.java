package com.packages.WinWheelBackendproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table
public class Selection implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;
    private String titre;
    @OneToOne
    @JsonIgnore
    private Jeu jeu;
    @OneToMany(mappedBy = "id")
    @JsonIgnore
    List<Cadeau> cadeaux;

    public Selection(Long id) {
        this.id = id;
    }

    public Selection(@JsonProperty("titre") String titre) {
        this.titre = titre;
    }
}
