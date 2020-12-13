package com.company.Domain.Utility;

import com.company.Enums.DirectionType;

public class Path {

    private Coordinate coordinate;
    private Velocity velocity;
    private int count;

    public Path(Coordinate coordinate, Velocity velocity, int count) {
        this.coordinate = coordinate;
        this.velocity = velocity;
        this.count = count;
    }

    //getters and setters below


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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
