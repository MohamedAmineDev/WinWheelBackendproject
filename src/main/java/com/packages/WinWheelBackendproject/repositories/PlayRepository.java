package com.packages.WinWheelBackendproject.repositories;

import com.packages.WinWheelBackendproject.models.Essai;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PlayRepository extends CrudRepository<Essai, Long> {
    Essai findBySelectionIdAndPlayerId(Long selectionId, Long playerId);

    /*@Query("select p from Essai p where p.playerId=:playerId and p.selection.id=:selectionId and p.dateDeLaPartie=:playedAt ")
    Essai findBySelectionIdAndByPlayerId(@Param("selectionId") Long selectionId, @Param("playerId") Long playerId, @Param("playedAt") LocalDate playedAt);*/
    @Query("select p from Essai p where p.player.id=:playerId and p.selection.id=:selectionId and p.dateDeLaPartie=:playedAt ")
    Essai findBySelectionIdAndByPlayerId(@Param("selectionId") Long selectionId, @Param("playerId") Long playerId, @Param("playedAt") LocalDate playedAt);
}
