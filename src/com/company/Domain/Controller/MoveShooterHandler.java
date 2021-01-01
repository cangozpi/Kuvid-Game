package com.company.Domain.Controller;

import com.company.Domain.Models.GunFactory;
import com.company.Enums.DirectionType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveShooterHandler implements KeyListener  {
    private final GunFactory gun;
    public MoveShooterHandler() { this.gun = GunFactory.getInstance(); }
    public void moveGun(DirectionType direction){
        gun.moveGun(direction);
    }

    @Override
    public void keyTyped(KeyEvent e) {}// Do not implement this

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 37){moveGun(DirectionType.LEFT);}
        if(e.getKeyCode() == 39){moveGun(DirectionType.RIGHT);}
    }

    @Override
    public void keyReleased(KeyEvent e) {}// Do not implement this
}
