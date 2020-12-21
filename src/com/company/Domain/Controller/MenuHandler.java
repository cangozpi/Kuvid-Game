package com.company.Domain.Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuHandler implements KeyListener {
    private Menu menu;
    public MenuHandler() {
        this.menu = Menu.getInstance();
    }
    public void restart(){
        menu.restart();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 80){ //P
            MenuWindowFactory menuFactory = new MenuWindowFactory();
           // MenuWindowFactory.render();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

}
