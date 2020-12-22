package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.ReactionBlockerType;

import javax.swing.*;
import java.awt.*;

import static com.company.UI.Objects.GameWindowFactory.L;

public class ReactionBlockerObject extends GameObject{
    private int angle;
    private String imageSource = "beta-b.png";

    public ReactionBlockerObject(Coordinate coordinate, int angle, ReactionBlockerType reactionBlockerType) {
        super(coordinate, "beta-b.png");
        this.angle = angle;
        switch (reactionBlockerType){
            case ALPHA_B:
                imageSource = "alpha-b.png";
                break;
            case BETA_B:
                imageSource = "beta-b.png";
                break;
            case GAMMA_B:
                imageSource = "gamma-b.png";
                break;
            case SIGMA_B:
                imageSource = "sigma-b.png";
                break;

        }
    }

    @Override
    public void draw() {
        ImageIcon gun = new ImageIcon(getClass().getResource("Assets/blockers/"+imageSource));
        Image scaledGun = gun.getImage().getScaledInstance(L/10, L/10, java.awt.Image.SCALE_SMOOTH); // scales the image
        ImageIcon gunIcon = new ImageIcon(scaledGun);
        JLabel gunLabel = new JLabel(gunIcon);
        //add to the JFrame
        this.add(gunLabel);
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
