package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject extends JPanel implements Drawable{

    protected Coordinate coordinate;
    protected int WIDTH;
    protected int HEIGHT;
    protected String imageSource;

    public GameObject(Coordinate coordinate, int WIDTH, int HEIGHT, String imageSource) {
        this.coordinate = coordinate;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.imageSource = imageSource;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
