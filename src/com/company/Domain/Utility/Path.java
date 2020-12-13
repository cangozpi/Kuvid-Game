package com.company.Domain.Utility;

import com.company.Enums.DirectionType;

public class Path {

    private Coordinate coordinate;
    private int angle;

    public Path(Coordinate coordinate, int angle) {
        this.coordinate = coordinate;
        this.angle = angle;
    }

    //getters and setters below
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getDirectionType() {
        return angle;
    }

    public void setDirectionType(int angle) {
        this.angle = angle;
    }
}
