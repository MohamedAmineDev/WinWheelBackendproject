package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.interfaces.ISelectionManagement;
import com.packages.WinWheelBackendproject.models.Selection;
import com.packages.WinWheelBackendproject.services.SelectionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("app/api/manage_selection")
public class SelectionController implements ISelectionManagement {
    @Autowired
    private SelectionService selectionService;

    @Override
    @GetMapping(path = "admin/{id}")
    public Selection getSelectionById(@PathVariable("id") Long id) {
        return selectionService.getSelectionById(id);
    }

    @Override
    @GetMapping(path = "admin/selections")
    public List<Selection> getAllSelections() {
        return selectionService.getAllSelections();
    }

    @Override
    @PostMapping(path = "admin/add_selection/{gameId}")
    public boolean addASelection(@RequestBody Selection selection, @PathVariable("gameId") Long gameId) {
        return selectionService.addASelection(selection, gameId);
    }

    @Override
    @PutMapping(path = "admin/update_selection/update_game/{idSelection}/{gameId}")
    public boolean setTheGameOfASelection(@PathVariable("idSelection") Long idSelection, @PathVariable("gameId") Long gameId) {
        return selectionService.setTheGameOfASelection(idSelection, gameId);
    }
}
