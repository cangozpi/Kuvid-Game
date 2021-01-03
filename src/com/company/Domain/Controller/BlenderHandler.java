package com.company.Domain.Controller;

import com.company.Domain.Models.Blender;
import com.company.Domain.Models.GameFactory;
import com.company.Enums.AtomType;
import com.company.UI.BlenderWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BlenderHandler implements KeyListener {
    private final Blender blender;
    public BlenderHandler() {
        this.blender = Blender.getInstance();
    }
    public void breakAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.breakAtoms(source, product, sourceAmount);
    }
    public void blendAtoms(AtomType source, AtomType product, int sourceAmount){
        blender.blendAtoms(source, product, sourceAmount);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 66){ //B
            BlenderWindow blenderFactory = new BlenderWindow();
            GameFactory.getInstance().pauseGame();
            blenderFactory.render();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
