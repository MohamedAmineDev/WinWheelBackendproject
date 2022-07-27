package com.packages.WinWheelBackendproject.repositories;

import com.packages.WinWheelBackendproject.models.Selection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SelectionRepository extends CrudRepository<Selection, Long> {
    Selection findByJeuId(Long id);

    Selection findByCadeauxId(Long id);

    @Query("select s from Selection s,Cadeau c where c.id=:giftId and c.selection.id=s.id")
    Selection findByGiftId(@Param("giftId") Long giftId);
}
