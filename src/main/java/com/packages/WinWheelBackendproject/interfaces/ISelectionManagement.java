package com.packages.WinWheelBackendproject.interfaces;

import com.packages.WinWheelBackendproject.models.Selection;

import java.util.List;

public interface ISelectionManagement {
    Selection getSelectionById(Long id);

    List<Selection> getAllSelections();

    boolean addASelection(Selection selection, Long gameId);

    boolean setTheGameOfASelection(Long idSelection, Long gameId);


}
