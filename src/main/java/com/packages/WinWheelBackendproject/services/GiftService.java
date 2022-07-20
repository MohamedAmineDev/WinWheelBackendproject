package com.packages.WinWheelBackendproject.services;

import com.packages.WinWheelBackendproject.interfaces.IGiftManagement;
import com.packages.WinWheelBackendproject.models.Cadeau;
import com.packages.WinWheelBackendproject.models.Selection;
import com.packages.WinWheelBackendproject.models.Utilisateur;
import com.packages.WinWheelBackendproject.repositories.GiftRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GiftService implements IGiftManagement {
    @Autowired
    private GiftRepository cadeauRepository;

    @Override
    public List<Cadeau> getAllGiftByUserId(Long idUser) {
        return cadeauRepository.findByAdminId(idUser);
    }

    @Override
    public boolean addGift(Cadeau cadeau, Long userId) {
        try {
            Utilisateur utilisateur = new Utilisateur(userId);
            cadeau.setAdmin(utilisateur);
            //utilisateur.getCadeaux().add(cadeau);
            cadeauRepository.save(cadeau);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean updateGift(Long id, Cadeau cadeau) {
        try {
            Cadeau sameGift = getGiftById(id);
            if (cadeau.getNom() != null && cadeau.getNom() != "" && cadeau.getNom() != sameGift.getNom()) {
                sameGift.setNom(cadeau.getNom());
            }
            if (cadeau.getDescription() != null && cadeau.getDescription() != "" && cadeau.getDescription() != sameGift.getDescription()) {
                sameGift.setDescription(cadeau.getDescription());
            }
            if (cadeau.getStock() != null && cadeau.getStock() > 0 && cadeau.getStock() != sameGift.getStock()) {
                sameGift.setStock(cadeau.getStock());
            }
            sameGift.setDateDernierModification(LocalDate.now());
            return cadeauRepository.save(sameGift) != null;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Cadeau getGiftById(Long id) {
        return cadeauRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addAGiftIntoTheSelection(Long idSelection, Long giftId) {
        try {
            Cadeau cadeau = getGiftById(giftId);
            Selection selection = new Selection(idSelection);
            cadeau.setSelection(selection);
            return cadeauRepository.save(cadeau) != null;
        } catch (Exception exception) {
            return false;
        }
    }
}
