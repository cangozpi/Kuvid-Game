package com.company.Domain.Models.MovementStrategy;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Domain.Utility.Velocity;


public abstract interface IMovementStrategy {
    public Path newPath(Path oldPath);
}
