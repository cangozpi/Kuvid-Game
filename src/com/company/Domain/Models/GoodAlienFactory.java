package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.UI.Objects.GameWindowFactory;

import java.util.*;

public class GoodAlienFactory {
    //Singleton Pattern
    private static GoodAlienFactory instance;
    Random random = new Random();
    //variables
    HashMap<PowerUpType, Integer> powerUpAmounts;
    HashMap<MoleculeType, Integer> moleculeAmounts;
    private GameFactory gameFactory;
    private MoleculeFactory moleculeFactory;

    private GoodAlienFactory() {
        gameFactory = GameFactory.getInstance();
    }

    public static GoodAlienFactory getInstance() {
        instance = instance == null ? new GoodAlienFactory() : instance;
        return instance;
    }

    //decrements the PowerUp value by one
    public void sendPowerUp() {
        int powerUpToShoot = random.nextInt(getAvailablePowerUps().size());
        PowerUpType type = getAvailablePowerUps().get(powerUpToShoot);
        Coordinate position = new Coordinate(random.nextInt(GameWindowFactory.windowWidth),0);

        powerUpAmounts.put(type, powerUpAmounts.get(type) - 1);
        //TODO: implement something that has to do with position
    }

    public Molecule sendMolecule() {
        int moleculeToShoot = random.nextInt(getAvailableMolecules().size());
        MoleculeType type = getAvailableMolecules().get(moleculeToShoot);
        Coordinate position = new Coordinate(random.nextInt(GameWindowFactory.windowWidth),0);

        moleculeAmounts.put(type, moleculeAmounts.get(type) - 1);
        return moleculeFactory.getInstance(position, new Velocity(270,gameFactory.getFallSpeed()), false, type , 30, 30);
    }

    public void setPowerUpAmount(HashMap<PowerUpType, Integer> powerUpAmount) {
        this.powerUpAmounts = powerUpAmount;
    }

    public void setMoleculeAmount(HashMap<MoleculeType, Integer> moleculeAmount) {
        this.moleculeAmounts = moleculeAmount;
    }
    public ArrayList<MoleculeType> getAvailableMolecules(){
        ArrayList<MoleculeType> availableMolecules = new ArrayList<>();
        MoleculeType molecules[] = (MoleculeType[]) moleculeAmounts.keySet().toArray();
        for(MoleculeType molecule: molecules){
            if (moleculeAmounts.get(molecule) != 0){
                availableMolecules.add(molecule);
            }
        }
        return availableMolecules;
    }

    public ArrayList<PowerUpType> getAvailablePowerUps(){
        ArrayList<PowerUpType> availablePowerUps = new ArrayList<>();
        PowerUpType powerUps[] = (PowerUpType[]) powerUpAmounts.keySet().toArray();
        for(PowerUpType powerUp: powerUps){
            if (powerUpAmounts.get(powerUp) != 0){
                availablePowerUps.add(powerUp);
            }
        }
        return availablePowerUps;
    }

}
