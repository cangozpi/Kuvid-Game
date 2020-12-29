package com.company.Domain.Controller;

import com.company.UI.BlenderWindowFactory;
import com.company.UI.MenuWindowFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 80){ //P
            MenuWindowFactory menuFactory = MenuWindowFactory.getInstance();
            menuFactory.render();
        }

        else if(e.getKeyCode() == 82){ //R
            MenuWindowFactory menuFactory = MenuWindowFactory.getInstance();
            menuFactory.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
