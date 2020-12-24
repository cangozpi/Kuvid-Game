package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.IProjectileType;
import com.company.Enums.PowerUpType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.IllegalPathStateException;

import static com.company.UI.Objects.GameWindowFactory.L;

public class PowerUpObject extends GameObject{
        private int angle;
        private String imageSource = "alpha-b.png";

        public PowerUpObject(Coordinate coordinate, int angle, IProjectileType powerUpType) {
            super(coordinate, "alpha-b.png");
            this.angle = angle;
            switch (powerUpType.toString()){
                case "ALPHA_powerUp":
                    imageSource = "+alpha-b.png";
                    break;
                case "BETA_powerUp":
                    imageSource = "+beta-b.png";
                    break;
                case "GAMMA_powerUp":
                    imageSource = "+gamma-b.png";
                    break;
                case "SIGMA_powerUp":
                    imageSource = "+sigma-b.png";
                    break;

            }
        }

        @Override
        public void draw() {
            ImageIcon powerUp = new ImageIcon(getClass().getResource("Assets/powerups/"+imageSource));
            Image scaledPowerUp = powerUp.getImage().getScaledInstance(L/10, L/10, java.awt.Image.SCALE_SMOOTH); // scales the image
            ImageIcon powerUpIcon = new ImageIcon(scaledPowerUp);
            JLabel powerUpLabel = new JLabel(powerUpIcon);
            //add to the JFrame
            this.add(powerUpLabel);
            GameWindowFactory.getInstance().getContentPane().add(this); // JFrame.add(JPanel)
            this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), L/10, L/10);
            this.setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

}
