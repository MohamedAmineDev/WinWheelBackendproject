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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
    //Attributes
    @EqualsAndHashCode.Include
    @Id
    @SequenceGenerator(
            name = "utilisateur_sequence",
            sequenceName = "utilisateur_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_sequence")
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;
    private LocalDate dateCreation;
    private String role;
    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private List<Cadeau> cadeaux;
    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private List<Jeu> jeux;
    @OneToMany(mappedBy = "id")
    private List<Essai> essais;

    //Constructor

    public Utilisateur(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("email") String email, @JsonProperty("address") String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.dateCreation = LocalDate.now();
    }

    public Utilisateur(Long id) {
        this.id = id;
    }
}
