package com.company.Domain.Controller;


import com.company.Domain.Models.AtomSelectorFactory;
import com.company.Domain.Models.AtomSelectorFactory;
import com.company.Domain.Models.GunFactory;

public class ShooterHandler {
    GunFactory gun;
    AtomSelectorFactory atomSelector;

    public ShooterHandler(){
        this.gun = GunFactory.getInstance();
        this.atomSelector = AtomSelectorFactory.getInstance();
    }

    public void shootGun() {
        gun.shootGun();
        atomSelector.selectAtom();
    }


}
