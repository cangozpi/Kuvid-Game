package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;

import javax.swing.*;
import java.awt.*;

public class AlphaAtomObject extends GameObject{
    private int angle;
    private String imageSource = "alpha.png";
    public AlphaAtomObject(Coordinate coordinate, int WIDTH, int HEIGHT, int angle) {
        super(coordinate, WIDTH, HEIGHT, "alpha.png");
        this.angle = angle;
    }

    @Override
    public void draw() {
        ImageIcon atom = new ImageIcon(getClass().getResource("Assets/atoms/"+imageSource));
        Image scaledAtom = atom.getImage().getScaledInstance(WIDTH, HEIGHT, java.awt.Image.SCALE_SMOOTH); // scales the image
        ImageIcon atomIcon = new ImageIcon(scaledAtom);
        JLabel atomLabel = new JLabel(atomIcon);
        //add to the JFrame
        this.add(atomLabel);
        GameWindowFactory.getInstance().getContentPane().add(this); // JFrame.add(JPanel)
        this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), WIDTH*10, HEIGHT*10);
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
