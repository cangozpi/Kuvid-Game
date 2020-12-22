package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.IProjectileType;
import com.company.UI.Observer.IGameListener;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject extends JPanel implements Drawable {

    protected Coordinate coordinate;
    protected String imageSource;

    public GameObject(Coordinate coordinate, String imageSource) {
        this.coordinate = coordinate;
        this.imageSource = imageSource;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
