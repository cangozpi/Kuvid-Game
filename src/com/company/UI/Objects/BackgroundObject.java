package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;

import javax.swing.*;
import java.awt.*;

import static com.company.UI.Objects.GameWindowFactory.*;

public class BackgroundObject extends GameObject{


    public BackgroundObject(Coordinate coordinate) {
        super(coordinate, "kuvid_bc.png");
    }

    @Override
    public void draw() {
        ImageIcon gun = new ImageIcon(getClass().getResource("Assets/"+imageSource));
        Image scaledGun = gun.getImage().getScaledInstance(GameWindowFactory.getExtendedWindowWidth(), GameWindowFactory.getWindowHeight(), java.awt.Image.SCALE_SMOOTH); // scales the image
        ImageIcon gunIcon = new ImageIcon(scaledGun);
        JLabel gunLabel = new JLabel(gunIcon);
        //add to the JFrame
        this.add(gunLabel);
        getInstance().getContentPane().add(this); // JFrame.add(JPanel)
        this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), GameWindowFactory.getExtendedWindowWidth(),  GameWindowFactory.getWindowHeight());
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
