package com.company.Domain.Models;



import com.company.Domain.Models.MovementStrategy.IMovementStrategy;
import com.company.Domain.Models.MovementStrategy.StraightStrategy;
import com.company.Domain.Models.MovementStrategy.ZigZagStrategy;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.IProjectileType;

public class MovementStrategyFactory {

    private double windowHeight= GameFactory.getInstance().getGameWindowHeight();;
    public MovementStrategyFactory(){

    }

    public IMovementStrategy getMovementStrategy(IProjectileType IProjectileType, Coordinate position){

        int y_position = (int) position.getYCoordinate();

        if (MoleculeType.ALPHA_1.equals(IProjectileType)|AtomType.ALPHA.equals(IProjectileType)) {
            return new ZigZagStrategy();
        }else if(MoleculeType.ALPHA_2.equals(IProjectileType)){
            return new ZigZagStrategy();
        }else if(MoleculeType.BETA_1.equals(IProjectileType)|AtomType.BETA.equals(IProjectileType)){
            return (y_position <= windowHeight/4) ? new StraightStrategy() : new ZigZagStrategy();
        }else if(MoleculeType.BETA_2.equals(IProjectileType)){
            return (y_position <= windowHeight/4) ? new StraightStrategy() : new ZigZagStrategy();
        }else if(MoleculeType.SIGMA.equals(IProjectileType)|AtomType.SIGMA.equals(IProjectileType)){
            return new StraightStrategy();
        }else if(MoleculeType.GAMMA.equals(IProjectileType)|AtomType.GAMMA.equals(IProjectileType)){
            return (y_position <= windowHeight/2) ? new StraightStrategy() : new ZigZagStrategy();
        }

        return null;
    }
}
