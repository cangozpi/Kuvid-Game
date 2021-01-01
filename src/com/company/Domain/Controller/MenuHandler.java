package com.company.Domain.Controller;

import com.company.Domain.Models.GameFactory;
import com.company.UI.MenuWindowFactory;
import com.company.UI.Objects.GameWindowFactory;

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
            GameFactory.getInstance().pauseGame();
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
