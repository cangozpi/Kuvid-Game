package com.company.Domain.Models.MovementStrategy;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;


public abstract interface IMovementStrategy {
    public Coordinate newPos(Coordinate position, Velocity velocity, boolean changeDirection);
}
