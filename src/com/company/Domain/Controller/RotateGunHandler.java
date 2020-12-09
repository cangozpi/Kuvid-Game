package com.company.Domain.Controller;
import com.company.Domain.Models.GunFactory;

import com.company.Enums.DirectionType;

public class RotateGunHandler {
        private GunFactory gun ;
        public RotateGunHandler(){this.gun = GunFactory.getInstance();}
        public void rotateGun(DirectionType direction){
            gun.rotateGun(direction);
        }
}
