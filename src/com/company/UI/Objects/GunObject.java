package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;

import javax.swing.*;
import java.awt.*;

public class GunObject extends GameObject {

    private int angle;

    public GunObject(Coordinate coordinate, int WIDTH, int HEIGHT, String imageSource, int angle) {
        super(coordinate, WIDTH, HEIGHT, imageSource);
        this.angle = angle;
    }

    @Override
    public void draw() {
        ImageIcon gun = new ImageIcon(getClass().getResource("Assets/"+imageSource));
        Image scaledGun = gun.getImage().getScaledInstance(WIDTH, HEIGHT, java.awt.Image.SCALE_SMOOTH); // scales the image
        ImageIcon gunIcon = new ImageIcon(scaledGun);
        JLabel gunLabel = new JLabel(gunIcon);
        //add to the JFrame
        this.add(gunLabel);
        GameWindowFactory.getInstance().getContentPane().add(this); // JFrame.add(JPanel)
        this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), WIDTH, HEIGHT);
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
