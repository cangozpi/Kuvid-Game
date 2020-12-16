package com.company.Domain.Models.MovementStrategy;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Domain.Utility.Velocity;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ZigZagStrategy implements IMovementStrategy{

    @Override
    public Path newPath(Path oldPath){
        double speed = oldPath.getVelocity().getSpeed();
        int angle = oldPath.getVelocity().getAngle();
        Coordinate oldPosition = oldPath.getCoordinate();
        int new_count = oldPath.getCount();
        if( new_count == 60){
            new_count = 0;
            if(angle == 225)
                angle = 315;
            else
                angle = 225;
        }
        new_count++;
        Coordinate new_coord = new Coordinate(oldPosition.getXCoordinate() + speed * cos(Math.toRadians(angle)),oldPosition.getYCoordinate() - speed * sin(Math.toRadians(angle)));
        Velocity new_velocity = new Velocity(angle,speed);

        Path new_path = new Path(new_coord,new_velocity,new_count) ;
        return new_path;

    }


}
