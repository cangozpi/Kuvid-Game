package com.company.UI;

import com.company.UI.Objects.BackgroundObject;
import com.company.UI.Objects.GunObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame implements ActionListener, MouseListener, KeyListener {
    public static final int gameWindowWidth = 852;
    public static final double gameWindowHeight = 480;
    public static final double L = gameWindowHeight / 10; // %10 of gameWindowHeight

    private final JPanel backgroundPanel;
    private final JPanel gunPanel;

    public Game(){
        backgroundPanel = new BackgroundObject();
        gunPanel = new GunObject();
    }

    //add Objects
    private void addObjects(){
        this.getContentPane().setBackground(Color.MAGENTA);
        this.getContentPane().add(gunPanel);//add object //TODO: add gun might use JLayeredPane
        this.getContentPane().add(backgroundPanel);//add background panel


    }

    //render Objects to the Frame
    public void render(){
        this.addObjects();//adds objects
        //setup frame
        this.setTitle("KUVID KAREL");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize( (int)gameWindowWidth, (int)gameWindowHeight);
        this.setResizable(false);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addKeyListener(this);


    }


    //Handle User Interactions
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
