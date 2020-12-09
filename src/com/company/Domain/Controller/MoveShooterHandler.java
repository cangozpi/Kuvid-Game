package com.company.Domain.Controller;

import com.company.Domain.Models.GunFactory;
import com.company.Enums.DirectionType;

public class MoveShooterHandler {
    private GunFactory gun;
    public MoveShooterHandler() { this.gun = GunFactory.getInstance(); }
    public void moveGun(DirectionType direction){
        gun.moveGun(direction);
    }
}
