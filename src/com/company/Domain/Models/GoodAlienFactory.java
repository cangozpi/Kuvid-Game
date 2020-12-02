package com.company.Domain.Models;

import com.company.Domain.Utility.Coordinate;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;

import java.util.Map;

public class GoodAlienFactory {
    //Singleton Pattern
    private static GoodAlienFactory instance;

    //variables
    Map<PowerUpType, Integer> powerUpAmounts;
    Map<MoleculeType, Integer> moleculeAmounts;

    private GoodAlienFactory(){

    }

    public static GoodAlienFactory getInstance(){
         instance = instance == null ?  new GoodAlienFactory() :  instance;
         return instance;
    }

    //decrements the PowerUp value by one
    public void sendPowerUp(PowerUpType type, Coordinate position ){
        powerUpAmounts.put(type, powerUpAmounts.get(type) - 1);
        //TODO: implement something that has to do with position
    }

    public void sendMolecule(MoleculeType type, Coordinate position){
        moleculeAmounts.put(type, moleculeAmounts.get(type) - 1);
        //TODO: implement something that has to do with position
    }

    public void setPowerUpAmount(Map<PowerUpType, Integer> powerUpAmount) {
        this.powerUpAmounts = powerUpAmount;
    }

    public void setMoleculeAmount(Map<MoleculeType, Integer> moleculeAmount) {
        this.moleculeAmounts = moleculeAmount;
    }
}
