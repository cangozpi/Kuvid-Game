package com.company.Domain.Utility;

import com.company.Enums.DirectionType;

public class Path {

    private Coordinate coordinate;
    private DirectionType directionType;

    public Path(Coordinate coordinate, DirectionType directionType) {
        this.coordinate = coordinate;
        this.directionType = directionType;
    }

    //getters and setters below
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public DirectionType getDirectionType() {
        return directionType;
    }

    public void setDirectionType(DirectionType directionType) {
        this.directionType = directionType;
    }
}
