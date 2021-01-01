package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;

import javax.swing.*;
import java.awt.*;

import static com.company.UI.Objects.GameWindowFactory.L;

public class AtomObject extends GameObject{
    private int angle;
    private String imageSource;
    public AtomObject(Coordinate coordinate, int angle, IProjectileType atomType) {
        super(coordinate, "alpha.png");
        this.angle = angle;
        switch (atomType.toString()){
            case "ALPHA_atom":
                imageSource = "alpha.png";
                break;
            case "BETA_atom":
                imageSource = "beta.png";
                break;
            case "GAMMA_atom":
                imageSource = "gamma.png";
                break;
            case "SIGMA_atom":
                imageSource = "sigma.png";
                break;
        }
    }
    @Override
    public void draw() {
        ImageIcon atom = new ImageIcon(getClass().getResource("Assets/atoms/"+imageSource));
        Image scaledAtom = atom.getImage().getScaledInstance(5*L/10, 5*L/10, java.awt.Image.SCALE_SMOOTH); // scales the image
        ImageIcon atomIcon = new ImageIcon(scaledAtom);
        JLabel atomLabel = new JLabel(atomIcon);
        //add to the JFrame
        this.add(atomLabel);
        GameWindowFactory.getInstance().getContentPane().add(this); // JFrame.add(JPanel)
        this.setBounds((int)coordinate.getXCoordinate(), (int)coordinate.getYCoordinate(), 5*L/10, 5*L/10);
        this.setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
