package com.packages.WinWheelBackendproject.repositories;

import com.packages.WinWheelBackendproject.models.Jeu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Jeu, Long> {
    List<Jeu> findByAdminId(Long id);

    Jeu findBySelectionId(Long id);
}
