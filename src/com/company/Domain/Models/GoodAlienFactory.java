package com.company.Domain.Models;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Random;

public class GoodAlienFactory {
    //Singleton Pattern
    private static GoodAlienFactory instance;
    Random random = new Random();
    //variables
    Map<PowerUpType, Integer> powerUpAmounts;
    Map<MoleculeType, Integer> moleculeAmounts;

    private GoodAlienFactory() {

    }

    public static GoodAlienFactory getInstance() {
        instance = instance == null ? new GoodAlienFactory() : instance;
        return instance;
    }

    //decrements the PowerUp value by one
    public void sendPowerUp(PowerUpType type, Coordinate position) {
        powerUpAmounts.put(type, powerUpAmounts.get(type) - 1);
        //TODO: implement something that has to do with position
    }

    public void sendMolecule() {
        int moleculeToShoot = random.nextInt(4);
        MoleculeType type = MoleculeType.GAMMA;
        if (moleculeToShoot == 0) {

        }

        //position = new Coordinate();
        moleculeAmounts.put(type, moleculeAmounts.get(type) - 1);
        //TODO: implement something that has to do with position
    }

    public void setPowerUpAmount(HashMap<PowerUpType, Integer> powerUpAmount) {
        this.powerUpAmounts = powerUpAmount;
    }

    public void setMoleculeAmount(HashMap<MoleculeType, Integer> moleculeAmount) {
        this.moleculeAmounts = moleculeAmount;
    }
    public Set<MoleculeType> getAvailableMolecules(){
        return moleculeAmounts.keySet();
    }
}
