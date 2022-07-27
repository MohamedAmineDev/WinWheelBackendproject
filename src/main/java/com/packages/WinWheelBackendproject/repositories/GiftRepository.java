package com.packages.WinWheelBackendproject.repositories;

import com.packages.WinWheelBackendproject.models.Cadeau;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GiftRepository extends CrudRepository<Cadeau, Long> {
    List<Cadeau> findByAdminId(Long id);

    List<Cadeau> findBySelectionId(Long id);
}
