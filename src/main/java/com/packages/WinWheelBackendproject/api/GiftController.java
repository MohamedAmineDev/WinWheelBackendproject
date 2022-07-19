package com.packages.WinWheelBackendproject.api;

import com.packages.WinWheelBackendproject.interfaces.IGiftManagement;
import com.packages.WinWheelBackendproject.models.Cadeau;
import com.packages.WinWheelBackendproject.services.GiftService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(path = "app/api/manage_gift")
public class GiftController implements IGiftManagement {
    @Autowired
    private GiftService giftService;

    @Override
    @GetMapping(path = "admin/{id}")
    public List<Cadeau> getAllGiftByUserId(@PathVariable("id") Long idUser) {
        return giftService.getAllGiftByUserId(idUser);
    }

    @Override
    @PostMapping(path = "admin/add_gift/{id}")
    public boolean addGift(@RequestBody Cadeau cadeau, @PathVariable("id") Long userId) {
        return giftService.addGift(cadeau, userId);
    }

    @Override
    @PutMapping(path = "admin/update_gift/{id}")
    public boolean updateGift(@PathVariable("id") Long id, @RequestBody Cadeau cadeau) {
        return giftService.updateGift(id, cadeau);
    }

    @Override
    @GetMapping(path = "gift/{id}")
    public Cadeau getGiftById(@PathVariable("id") Long id) {
        return giftService.getGiftById(id);
    }
}

