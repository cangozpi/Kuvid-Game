package com.company.Domain.Controller;
import com.company.Domain.Models.GunFactory;

import com.company.Enums.DirectionType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RotateGunHandler {
        private GunFactory gun ;
        public RotateGunHandler(){this.gun = GunFactory.getInstance();}
        public void rotateGun(DirectionType direction){
            gun.rotateGun(direction);
        }


}
