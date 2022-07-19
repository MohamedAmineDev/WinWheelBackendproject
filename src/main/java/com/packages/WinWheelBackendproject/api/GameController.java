package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.interfaces.IGameManagement;
import com.packages.WinWheelBackendproject.models.Jeu;
import com.packages.WinWheelBackendproject.services.GameService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("app/api/manage_game")
public class GameController implements IGameManagement {
    @Autowired
    private GameService gameService;

    @Override
    @GetMapping(path = "game/{id}")
    public Jeu getGameById(@PathVariable("id") Long id) {
        return gameService.getGameById(id);
    }

    @Override
    @GetMapping(path = "admin/{id}")
    public List<Jeu> getGamesByUserId(@PathVariable("id") Long userId) {
        return gameService.getGamesByUserId(userId);
    }

    @Override
    @PostMapping(path = "admin/add_game/{id}")
    public boolean addAGame(@PathVariable("id") Long userId, @RequestBody Jeu jeu) {
        return gameService.addAGame(userId, jeu);
    }
}
