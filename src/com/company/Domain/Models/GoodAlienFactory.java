package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Models.Projectile.PowerUp;
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
        moleculeFactory = new MoleculeFactory();
    }

    public static GoodAlienFactory getInstance() {
        instance = instance == null ? new GoodAlienFactory() : instance;
        return instance;
    }

    //decrements the PowerUp value by one
    public PowerUp sendPowerUp() {
        List<PowerUpType> availablePowerUps = getAvailablePowerUps();
        if(!availablePowerUps.isEmpty()) {
            int powerUpToShoot = random.nextInt(availablePowerUps.size());
            PowerUpType type = availablePowerUps.get(powerUpToShoot);
            Coordinate position = new Coordinate(random.nextInt(GameWindowFactory.windowWidth), 0);

            powerUpAmounts.put(type, powerUpAmounts.get(type) - 1);
            return new PowerUp(position, new Velocity(270, gameFactory.getFallSpeed()), type, false, 30, 30);
        }
        return null;
    }

    public Molecule sendMolecule() {
        List<MoleculeType> availableMolecules = getAvailableMolecules();
        if(!availableMolecules.isEmpty()){
            int moleculeToShoot = random.nextInt(availableMolecules.size());
            MoleculeType type = availableMolecules.get(moleculeToShoot);
            Coordinate position = new Coordinate(random.nextInt(GameWindowFactory.windowWidth),0);

            moleculeAmounts.put(type, moleculeAmounts.get(type) - 1);
            return moleculeFactory.getInstance(position, new Velocity(270,gameFactory.getFallSpeed()), false, type , 30, 30);
        }
        return null;
    }

    public void setPowerUpAmount(HashMap<PowerUpType, Integer> powerUpAmount) {
        this.powerUpAmounts = powerUpAmount;
    }

    public void setMoleculeAmount(HashMap<MoleculeType, Integer> moleculeAmount) {
        this.moleculeAmounts = moleculeAmount;
    }
    public List<MoleculeType> getAvailableMolecules(){
        ArrayList<MoleculeType> availableMolecules = new ArrayList<>();
        for (Map.Entry<MoleculeType, Integer> entry : moleculeAmounts.entrySet()) {
            if (entry.getValue() != 0) {
                availableMolecules.add(entry.getKey());
            }
        }
        return availableMolecules;
    }

    public List<PowerUpType> getAvailablePowerUps(){
        ArrayList<PowerUpType> availablePowerUps = new ArrayList<>();
        for (Map.Entry<PowerUpType, Integer> entry : powerUpAmounts.entrySet()) {
            if (entry.getValue() != 0) {
                availablePowerUps.add(entry.getKey());
            }
        }
        return availablePowerUps;
    }

}
