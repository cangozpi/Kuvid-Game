package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.MoleculeType;

import javax.swing.*;
import java.awt.*;

import static com.company.UI.Objects.GameWindowFactory.L;

public class MoleculeObject extends GameObject{
        private int angle;
        private String imageSource = "alpha-1.png";

        public MoleculeObject(Coordinate coordinate, int angle, MoleculeType moleculeType) {
            super(coordinate, "alpha-1.png");
            this.angle = angle;
            switch (moleculeType){
                case ALPHA_1:
                    imageSource = "alpha-1.png";
                    break;
                case BETA_1:
                    imageSource = "beta-1.png";
                    break;
                case ALPHA_2:
                    imageSource = "alpha-2.png";
                    break;
                case BETA_2:
                    imageSource = "beta-2.png";
                    break;
                case GAMMA:
                    imageSource = "gamma-.png";
                    break;
                case SIGMA:
                    imageSource = "sigma-.png";
                    break;

            }
        }

        @Override
        public void draw() {
            ImageIcon molecule = new ImageIcon(getClass().getResource("Assets/molecules/"+imageSource));
            Image scaledMolecule = molecule.getImage().getScaledInstance(5*L/4, 5*L/4, java.awt.Image.SCALE_SMOOTH); // scales the image
            ImageIcon moleculeIcon = new ImageIcon(scaledMolecule);
            JLabel moleculeLabel = new JLabel(moleculeIcon);
            //add to the JFrame
            this.add(moleculeLabel);
            GameWindowFactory.getInstance().getContentPane().add(this); // JFrame.add(JPanel)
            this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), 5*L/4, 5*L/4);
            this.setOpaque(false);
        }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}

