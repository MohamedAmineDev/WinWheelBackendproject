package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.interfaces.IPlayManagement;
import com.packages.WinWheelBackendproject.models.Essai;
import com.packages.WinWheelBackendproject.services.PlayService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("app/api/manage_playing")
public class PlayController implements IPlayManagement {
    @Autowired
    private PlayService playService;

    @Override
    @GetMapping(path = "player/already_played/{playerId}/{gameId}")
    public boolean alreadyPlayedTheGame(@PathVariable("playerId") Long playerId, @PathVariable("gameId") Long gameId) {
        return playService.alreadyPlayedTheGame(playerId, gameId);
    }

    @Override
    @PostMapping(path = "player/played_the_game/{playerId}/{gameId}")
    public boolean playTheGame(@PathVariable("playerId") Long playerId, @PathVariable("gameId") Long gameId, @RequestBody Essai essai) {
        return playService.playTheGame(playerId, gameId, essai);
    }
}
