package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;

public abstract class Projectile {
    Coordinate coordinate;
    Velocity velocity;
    public Projectile(Coordinate coordinate, Velocity velocity){
        this.coordinate = coordinate;
        this.velocity = velocity;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}
