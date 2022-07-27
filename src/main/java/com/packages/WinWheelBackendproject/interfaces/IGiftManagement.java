package com.packages.WinWheelBackendproject.interfaces;

import com.packages.WinWheelBackendproject.models.Cadeau;

import java.util.List;

public interface IGiftManagement {
    List<Cadeau> getAllGiftByUserId(Long idUser);

    boolean addGift(Cadeau cadeau, Long userId);

    boolean updateGift(Long id, Cadeau cadeau);

    Cadeau getGiftById(Long id);

    boolean addAGiftIntoTheSelection(Long idSelection, Long giftId);

    List<Cadeau> getAllGiftsOfASelection(Long selectionId);
}
