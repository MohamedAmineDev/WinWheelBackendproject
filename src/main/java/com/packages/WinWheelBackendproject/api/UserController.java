package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.services.PlayerService;
import com.packages.WinWheelBackendproject.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("app/api/manage_user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "user/{id}")
    public Utilisateur getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping(path = "update_user/{id}")
    public boolean updateAUser(@PathVariable("id") Long id, @RequestBody Utilisateur utilisateur) {
        return userService.updateAUser(id, utilisateur);
    }

    @PutMapping(path = "update_user/change_password/{email}")
    public boolean updateAPassword(@PathVariable("email") String email, @RequestBody Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return userService.ChangePassword(email, utilisateur);
    }

    @PostMapping(path = "user/check_user")
    public boolean checkUser(@RequestBody Utilisateur utilisateur) {
        //utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        //System.out.println(utilisateur.getPassword());
        return userService.findUserByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());
    }

    @GetMapping(path = "user/username/{email}")
    public Utilisateur getUsername(@PathVariable("email") String email) {
        return userService.getUsername(email);
    }
}
