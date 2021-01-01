package com.company.Domain.Controller;

import com.company.Domain.Models.GunFactory;

import com.company.Enums.DirectionType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RotateGunHandler implements KeyListener{
        private final GunFactory gun ;
        public RotateGunHandler(){this.gun = GunFactory.getInstance();}
        public void rotateGun(DirectionType direction){
            gun.rotateGun(direction);
        }

    @Override
    public void keyTyped(KeyEvent e) {}// Do not implement this

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 65){rotateGun(DirectionType.ANTICLOCKWISE);}//A
        if(e.getKeyCode() == 68){rotateGun(DirectionType.CLOCKWISE);}//D
        }

    @Override
    public void keyReleased(KeyEvent e) {}// Do not implement this

}
