package com.company.Domain.Models.MovementStrategy;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Domain.Utility.Velocity;

public class StraightStrategy implements IMovementStrategy {

    @Override
    public Path newPath(Path old_path){
        Coordinate old_position = old_path.getCoordinate();
        double speed = old_path.getVelocity().getSpeed();

        return new Path(new Coordinate(old_position.getXCoordinate(),old_position.getYCoordinate() + speed),old_path.getVelocity(),old_path.getCount());

    }

}
