package com.company.Domain.Models;



import com.company.Domain.Models.MovementStrategy.IMovementStrategy;
import com.company.Domain.Models.MovementStrategy.StraightStrategy;
import com.company.Domain.Models.MovementStrategy.ZigZagStrategy;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.MovementType;
import com.company.Enums.ProjectileType;

public class MovementStrategyFactory {

    private double windowHeight= GameFactory.getInstance().getGameWindowHeight();;
    public MovementStrategyFactory(){

    }

    public IMovementStrategy getMovementStrategy(ProjectileType projectileType, Coordinate position){

        int y_position = (int) position.getYCoordinate();

        if (MoleculeType.ALPHA_1.equals(projectileType)|AtomType.ALPHA.equals(projectileType)) {
            return new ZigZagStrategy();
        }else if(MoleculeType.ALPHA_2.equals(projectileType)){
            return new ZigZagStrategy();
        }else if(MoleculeType.BETA_1.equals(projectileType)|AtomType.BETA.equals(projectileType)){
            return (y_position <= windowHeight/4) ? new StraightStrategy() : new ZigZagStrategy();
        }else if(MoleculeType.BETA_2.equals(projectileType)){
            return (y_position <= windowHeight/4) ? new StraightStrategy() : new ZigZagStrategy();
        }else if(MoleculeType.SIGMA.equals(projectileType)|AtomType.SIGMA.equals(projectileType)){
            return new StraightStrategy();
        }else if(MoleculeType.GAMMA.equals(projectileType)|AtomType.GAMMA.equals(projectileType)){
            return (y_position <= windowHeight/2) ? new StraightStrategy() : new ZigZagStrategy();
        }

        return null;
    }
}
