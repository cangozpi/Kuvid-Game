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
        this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), WIDTH*6, HEIGHT);
        this.setOpaque(false);

    }
    public void rotation(Graphics g){
        System.out.println(Math.toRadians(angle));
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(-angle+90),WIDTH*3, HEIGHT);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rotation(g);
    }

}