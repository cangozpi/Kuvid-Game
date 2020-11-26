package com.company.UI.Objects;

import com.company.UI.Game;

import javax.swing.*;
import java.awt.*;

public class GunObject extends JPanel {

    public GunObject() {
        ImageIcon image = new ImageIcon(this.getClass().getResource("Resources/GunImage.png"));
        ImageIcon scaledImage = new ImageIcon(image.getImage());
        JLabel label = new JLabel(scaledImage, JLabel.CENTER);
        this.add(label);
        this.setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
