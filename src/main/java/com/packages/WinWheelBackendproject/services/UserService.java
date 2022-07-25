package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public boolean updateAUser(Long id, Utilisateur utilisateur) {
        try {
            Utilisateur sameUser = utilisateurRepository.findById(id).orElse(null);
            if (utilisateur.getAddress() != null && utilisateur.getAddress() != "" && utilisateur.getAddress() != sameUser.getAddress()) {
                sameUser.setAddress(utilisateur.getAddress());
            }
            if (utilisateur.getUsername() != null && utilisateur.getUsername() != "" && utilisateur.getUsername() != sameUser.getUsername()) {
                sameUser.setUsername(utilisateur.getUsername());
            }
            return utilisateurRepository.save(sameUser) != null;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean ChangePassword(Long id, Utilisateur utilisateur) {
        try {
            Utilisateur sameUser = getUserById(id);
            sameUser.setPassword(utilisateur.getPassword());
            return utilisateurRepository.save(sameUser) != null;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean findUserByEmailAndPassword(String email, String password) {
        return true;
    }

    public Utilisateur getUsername(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
