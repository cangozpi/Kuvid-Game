package com.company.Domain.Models.MovementStrategy;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Domain.Utility.Velocity;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ZigZagStrategy implements IMovementStrategy{

    @Override
    public Path newPath(Coordinate position, Velocity velocity, boolean changeDirection){
        double speed = velocity.getSpeed();
        int angle = velocity.getAngle();
        if( changeDirection){
            if(angle == 225)
                angle = 315;
            else
                angle = 225;
        }
        return new Path(null,0);
    }


}