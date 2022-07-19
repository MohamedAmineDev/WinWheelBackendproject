package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.services.PlayerService;
import com.packages.WinWheelBackendproject.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("app/api/manage_user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "user/{id}")
    public Utilisateur getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }
}
