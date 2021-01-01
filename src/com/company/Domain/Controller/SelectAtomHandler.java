package com.company.Domain.Controller;
import com.company.Domain.Models.AtomSelector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SelectAtomHandler implements KeyListener  {
    private final AtomSelector atomSelector;

    public SelectAtomHandler() {
        this.atomSelector = new AtomSelector();
    }

    public void selectAtom() {
        atomSelector.selectAtom();

    }

    @Override
    public void keyTyped(KeyEvent e) {// Do not implement this

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        if(e.getKeyCode() == 67){ //C
            selectAtom();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) { // Do not implement this

    }

}