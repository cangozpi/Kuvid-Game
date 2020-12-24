package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.PowerUpType;

import javax.swing.*;
import java.awt.*;

import static com.company.UI.Objects.GameWindowFactory.L;

public class PowerUpObject extends GameObject{
        private int angle;
        private String imageSource = "alpha-b.png";

        public PowerUpObject(Coordinate coordinate, int angle, PowerUpType powerUpType) {
            super(coordinate, "alpha-b.png");
            this.angle = angle;
            switch (powerUpType){
                case ALPHA:
                    imageSource = "+alpha-b.png";
                    break;
                case BETA:
                    imageSource = "+beta-b.png";
                    break;
                case GAMMA:
                    imageSource = "+gamma-b.png";
                    break;
                case SIGMA:
                    imageSource = "+sigma-b.png";
                    break;

            }
        }

        @Override
        public void draw() {
            ImageIcon powerUp = new ImageIcon(getClass().getResource("Assets/powerups/"+imageSource));
            Image scaledPowerUp = powerUp.getImage().getScaledInstance(5*L/4, 5*L/4, java.awt.Image.SCALE_SMOOTH); // scales the image
            ImageIcon powerUpIcon = new ImageIcon(scaledPowerUp);
            JLabel powerUpLabel = new JLabel(powerUpIcon);
            //add to the JFrame
            this.add(powerUpLabel);
            GameWindowFactory.getInstance().getContentPane().add(this); // JFrame.add(JPanel)
            this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), 5*L/4, 5*L/4);
            this.setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

}
