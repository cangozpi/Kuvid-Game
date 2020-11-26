package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;

public class Ammo {
    //instance variables
    private Coordinate coordinate;
    private Velocity velocity;

    public Ammo(Coordinate coordinate, Velocity velocity) {
        this.coordinate = coordinate;
        this.velocity = velocity;
    }

    //getters and setters
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}
