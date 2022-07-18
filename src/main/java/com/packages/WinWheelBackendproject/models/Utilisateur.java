package com.packages.WinWheelBackendproject.models;

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
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
    //Attributes
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;
    private LocalDate dateCreation;
    private String role;

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
