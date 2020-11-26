package com.company.UI.Objects;

import com.company.UI.Game;

import javax.swing.*;
import java.awt.*;

public class BackgroundObject extends JPanel {

    public BackgroundObject() {
        ImageIcon image = new ImageIcon(this.getClass().getResource("Resources/BackgroundImage.png"));
        ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance((int)Game.gameWindowWidth, (int)Game.gameWindowHeight , Image.SCALE_SMOOTH));
        JLabel label = new JLabel(scaledImage, JLabel.CENTER);
        this.add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
