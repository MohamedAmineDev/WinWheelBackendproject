package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.interfaces.IUserManagement;
import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.services.EmailingService;
import com.packages.WinWheelBackendproject.services.PlayerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(path = "app/api/manage_player")
public class PlayerController implements IUserManagement {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private EmailingService emailingService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @GetMapping(path = "players")
    public List<Utilisateur> getAllUsers() {
        return playerService.getAllUsers();
    }

    @Override
    @PostMapping(path = "add_player")
    public boolean addAUser(@RequestBody Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        boolean test = playerService.addAUser(utilisateur);
        if (test) {
            emailingService.sendAnEmail(utilisateur.getEmail(), "Account creation", "Good morning!\nThis is to informe you that your account has been created successfully !\nHave a nice day !");
        }
        return test;
    }
}
