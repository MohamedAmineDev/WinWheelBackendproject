package com.packages.WinWheelBackendproject.repositories;

import com.packages.WinWheelBackendproject.models.Selection;
import org.springframework.data.repository.CrudRepository;

public interface SelectionRepository extends CrudRepository<Selection, Long> {
    Selection findByJeuId(Long id);

    Selection findByCadeauxId(Long id);
}
