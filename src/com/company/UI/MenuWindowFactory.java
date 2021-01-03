package com.company.UI;

import com.company.Domain.Controller.MenuHandler;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.Inventory;
import com.company.Domain.Models.Repository.DataTransfer;

import com.company.Utils.CenterWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MenuWindowFactory extends JFrame implements KeyListener {

    public static int windowWidth = 720;
    public static int windowHeight = 360;
    private static MenuWindowFactory factoryInstance;
    private MenuHandler menuHandler;
    private Inventory inventory;
    private JPanel[][] panelHolder;
    private DataTransfer dataTransfer;



    //singleton instance
    private static MenuWindowFactory instance;

    private MenuWindowFactory() {
        dataTransfer = new DataTransfer();
    }

    public static MenuWindowFactory getInstance(){
        if(instance == null){
            instance = new MenuWindowFactory();
            return instance;
        } else{
            return instance;
        }
    }

    public void render() {
        //factoryInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowWidth, windowHeight);
        this.setTitle("KUVid Menu");
        this.setLayout(new FlowLayout());
        this.setBackground(Color.DARK_GRAY);

        CenterWindow.centerWindow(this);
        JPanel inputPanel = new JPanel();
        int rows = 1;
        int columns = 1;

        inputPanel.setLayout(new GridLayout(rows, columns));

        panelHolder = new JPanel[rows][columns];

        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < columns; n++) {
                panelHolder[m][n] = new JPanel();
                inputPanel.add(panelHolder[m][n]);
            }
        }
        panelHolder[0][0].add(new JLabel("Game Paused"));
        this.getContentPane().removeAll();
        this.getContentPane().add(inputPanel);
        this.setVisible(true);

        //add keyListener to the JFrame
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 82){ //R
            getInstance().dispose();
            GameFactory.getInstance().resumeGame();
        }

        else if(e.getKeyCode() == 83){ //S
            dataTransfer.saveGame();
        }

        else if(e.getKeyCode() == 76){ //L
            dataTransfer.loadGame();
            getInstance().dispose();
            GameFactory.getInstance().resumeGame();
            //reset game UI
            GameFactory.getInstance().resetObjectLists();

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
