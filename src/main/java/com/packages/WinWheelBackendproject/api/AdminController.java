package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.interfaces.IUserManagement;
import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(path = "app/api/manage_admin")
public class AdminController implements IUserManagement {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @GetMapping(path = "admins")
    public List<Utilisateur> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Override
    @PostMapping(path = "add_admin")
    public boolean addAUser(@RequestBody Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return adminService.addAUser(utilisateur);
    }
}
