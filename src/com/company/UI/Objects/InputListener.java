package com.company.UI.Objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {// Do not implement this

    }
    @Override
    public void keyReleased(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 37){ //Left Arrow
            GameWindowFactory.getInstance().leftArrow();
        }

        if(e.getKeyCode() == 39){ //Right Arrow
            GameWindowFactory.getInstance().rightArrow();
        }

        if(e.getKeyCode() == 38){ //Up Arrow
            GameWindowFactory.getInstance().upArrow();
        }

        if(e.getKeyCode() == 65){ //A
            GameWindowFactory.getInstance().rotateLeft();
        }

        if(e.getKeyCode() == 68){ //D
            GameWindowFactory.getInstance().rotateRight();
        }

        if(e.getKeyCode() == 67){ //C
            GameWindowFactory.getInstance().selectAtom();
        }

        if(e.getKeyCode() == 80){ //P
            GameWindowFactory.getInstance().pause();
        }
    }


}
