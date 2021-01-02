package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;

import javax.swing.*;
import java.awt.*;



public class GunObject extends GameObject {
    private double L = GameWindowFactory.getL();
    private int gunWidth;
    private int gunHeight;
    private int angle;
    public GunObject(Coordinate coordinate, int angle) {
        super(coordinate, "shooter.png");
        this.angle = angle;
        this.gunWidth = (int)(L / 2);
        this.gunHeight = (int)L;
    }

    @Override
    public void draw() {
        ImageIcon gun = new ImageIcon(getClass().getResource("Assets/" + imageSource));
        Image scaledGun = gun.getImage().getScaledInstance(gunWidth, gunHeight, java.awt.Image.SCALE_SMOOTH); // scales the image
        ImageIcon gunIcon = new ImageIcon(scaledGun);
        JLabel gunLabel = new JLabel(gunIcon);
        //add to the JFrame
        this.add(gunLabel);
        GameWindowFactory.getInstance().getContentPane().add(this); // JFrame.add(JPanel)
        this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), gunWidth*6, gunHeight);
        this.setOpaque(false);

    }
    public void rotation(Graphics g){
        System.out.println(Math.toRadians(angle));
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(-angle+90),gunWidth*3, gunHeight);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rotation(g);
    }

}
