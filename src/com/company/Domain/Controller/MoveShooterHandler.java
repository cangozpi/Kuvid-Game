package com.company.Domain.Controller;

import com.company.Domain.Models.GunFactory;
import com.company.Enums.DirectionType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveShooterHandler  {
    private GunFactory gun;
    public MoveShooterHandler() { this.gun = GunFactory.getInstance(); }
    public void moveGun(DirectionType direction){
        gun.moveGun(direction);
    }

}
