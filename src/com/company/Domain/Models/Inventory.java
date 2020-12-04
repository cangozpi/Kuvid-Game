package com.company.Domain.Models;
import com.company.Enums.AtomType;
import com.company.Enums.PowerUpType;
import java.util.HashMap;


public class Inventory {
    private static Inventory inventory = null;
    HashMap<AtomType,Integer> atomMap;
    HashMap<PowerUpType,Integer> powerUpMap;


    private Inventory(){}
    public static Inventory getInstance(){
        if(inventory==null) inventory = new Inventory();
        return inventory;
    }

    public void setAtomMap(HashMap<AtomType, Integer> atomMap) {
        this.atomMap = atomMap;
    }

    public void setPowerUpMap(HashMap<PowerUpType, Integer> powerUpMap) {
        this.powerUpMap = powerUpMap;
    }

    public int getAtomNumber(AtomType atomType){
        switch (atomType){
            case ALPHA:
                return atomMap.get(AtomType.ALPHA);
            case BETA:
                return atomMap.get(AtomType.BETA);
            case GAMMA:
                return atomMap.get(AtomType.GAMMA);
            case SIGMA:
                return atomMap.get(AtomType.SIGMA);
        }
        throw new IllegalArgumentException("Atom Type does not exist.");
    }

    public int getPowerUpNumber(PowerUpType powerUpType){
        switch (powerUpType){
            case ALPHA:
                return atomMap.get(AtomType.ALPHA);
            case BETA:
                return atomMap.get(AtomType.BETA);
            case GAMMA:
                return atomMap.get(AtomType.GAMMA);
            case SIGMA:
                return atomMap.get(AtomType.SIGMA);
        }
        throw new IllegalArgumentException("PowerUp Type does not exist.");
    }
    public void addAtom(AtomType atomType){
        switch (atomType){
            case ALPHA:
                 atomMap.merge(AtomType.ALPHA,1,Integer::sum);
            case BETA:
                atomMap.merge(AtomType.BETA,1,Integer::sum);
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,1,Integer::sum);
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,1,Integer::sum);
        }
    }
    public void addAtom(AtomType atomType, int n){
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,n,Integer::sum);
            case BETA:
                atomMap.merge(AtomType.BETA,n,Integer::sum);
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,n,Integer::sum);
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,n,Integer::sum);
        }
    }
    public void addPowerUp(PowerUpType powerUpType){
        switch (powerUpType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,1,Integer::sum);
            case BETA:
                atomMap.merge(AtomType.BETA,1,Integer::sum);
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,1,Integer::sum);
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,1,Integer::sum);
        }
    }
    public void addPowerUp(PowerUpType powerUpType, int n){
        switch (powerUpType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,n,Integer::sum);
            case BETA:
                atomMap.merge(AtomType.BETA,n,Integer::sum);
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,n,Integer::sum);
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,n,Integer::sum);
        }
    }
    public boolean removeAtom(AtomType atomType){
        if(atomMap.get(atomType)-1 < 0)return false;
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,-1,Integer::sum);
            case BETA:
                atomMap.merge(AtomType.BETA,-1,Integer::sum);
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,-1,Integer::sum);
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,-1,Integer::sum);
        }
        return true;
    }
    public boolean removeAtom(AtomType atomType, int n){
        if(atomMap.get(atomType)-n < 0)return false;
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,n,Integer::sum);
            case BETA:
                atomMap.merge(AtomType.BETA,n,Integer::sum);
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,n,Integer::sum);
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,n,Integer::sum);
        }
        return true;
    }
    public boolean removePowerUp(AtomType atomType){
        if(atomMap.get(atomType)-1 < 0)return false;
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,-1,Integer::sum);
            case BETA:
                atomMap.merge(AtomType.BETA,-1,Integer::sum);
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,-1,Integer::sum);
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,-1,Integer::sum);
        }
        return true;
    }
}
