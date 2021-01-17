package com.company.Domain.Models.Projectile;


import com.company.Domain.Models.MovementStrategyFactory;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.IProjectileType;
import com.company.Enums.MoleculeType;


public class Molecule extends Projectile{
    private MoleculeType moleculeType;
    private Path path;
    public Molecule(Coordinate coordinate, Velocity velocity, MoleculeType moleculeType,boolean isAmmo,  int height, int width) {
        super(coordinate, velocity, isAmmo, height, width);
        this.moleculeType = moleculeType;
        this.path = new Path(coordinate, velocity, 30);



    }
    public MoleculeType getMoleculeType(){
        return this.moleculeType;
    }
    public IProjectileType getProjectileType() { return moleculeType; }

    @Override
    public void move() {
        MovementStrategyFactory strategyFactory = new MovementStrategyFactory();

        this.path = strategyFactory.getMovementStrategy(getMoleculeType(),getCoordinate()).newPath(this.path);
        setCoordinate(path.getCoordinate());
    }
}
