package com.packages.WinWheelBackendproject.interfaces;

import com.packages.WinWheelBackendproject.models.Utilisateur;

import java.util.List;

public interface IUserManagement {

    List<Utilisateur> getAllUsers();

    boolean addAUser(Utilisateur utilisateur);

    
}
