package com.company.Domain.Controller;


import com.company.Domain.Models.AtomSelector;
import com.company.Domain.Models.GunFactory;
import com.company.Domain.Models.Inventory;
import com.company.Enums.IProjectileType;
import com.company.Enums.PowerUpType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ShooterHandler implements KeyListener {
    GunFactory gun;
    AtomSelector atomSelector;
    Inventory inventory;

    public ShooterHandler(){
        this.gun = GunFactory.getInstance();
        this.atomSelector = new AtomSelector();
        this.inventory = Inventory.getInstance();
    }

    public void shootGun() {
        gun.shootGun();
        atomSelector.selectAtom();
    }

    @Override
    public void keyTyped(KeyEvent e) {// Do not implement this

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        if(e.getKeyCode() == 38){ //Up Arrow
            shootGun();
        }



    }



    @Override
    public void keyReleased(KeyEvent e) { // Do not implement this

    }


}
