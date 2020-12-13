package com.company.Domain.Models;



import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Enums.AtomType;
import com.company.Enums.MovementType;
import com.company.Enums.ProjectileType;

public class MovementStrategyFactory {

    public MovementStrategyFactory(){

    }
    public Path getMovementStrategy(ProjectileType projectileType, Coordinate position){
        switch (projectileType.toString()){


        }
        return null;
    }
}
