package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.repositories.UtilisateurRepository;
import com.packages.WinWheelBackendproject.security.MyUserDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Cet utilisateur n'existe pas dans note base de données");
        }

        return new MyUserDetails(utilisateur);
    }
}
