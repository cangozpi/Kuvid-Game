package com.company.Domain.Models;
import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;
import com.company.Enums.PowerUpType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Inventory {
    private static Inventory inventory = null;
    HashMap<AtomType,Integer> atomMap;
    HashMap<PowerUpType,Integer> powerUpMap;
    ArrayList<Projectile> shieldedAtomList;

    private Inventory(){
        powerUpMap = new HashMap<>();
        shieldedAtomList = new ArrayList<>();
        Arrays.stream(PowerUpType.values()).forEach((x) -> powerUpMap.put(x, 0));

    }

    public static Inventory getInstance(){
        if(inventory==null) inventory = new Inventory();
        return inventory;
    }

    public void setAtomMap(HashMap<AtomType, Integer> atomMap) {
        this.atomMap = atomMap;
    }

    public void setPowerUpMap(HashMap<PowerUpType, Integer> powerUpMap) {this.powerUpMap = powerUpMap;}


    public int getShieldedAtomAmount() {if(shieldedAtomList!=null) {return shieldedAtomList.size();}else return 0;}

    public Projectile getShieldedAtom(int index){return shieldedAtomList.remove(index);}

    public int getAtomAmount(AtomType atomType){
        if(atomMap==null) {
            System.err.println("Atom Map is not initialized.");
            return -1;
        }
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
        return -1;
    }

    public int getPowerUpAmount(PowerUpType powerUpType){
        if(powerUpMap==null) {
            System.err.println("PowerUp Map is not initialized.");
            return -1;
        }
        switch (powerUpType){
            case ALPHA:
                return powerUpMap.get(powerUpType.ALPHA);
            case BETA:
                return powerUpMap.get(powerUpType.BETA);
            case GAMMA:
                return powerUpMap.get(powerUpType.GAMMA);
            case SIGMA:
                return powerUpMap.get(powerUpType.SIGMA);
        }
        return -1;
    }
    public void addAtom(AtomType atomType){
        if(atomMap==null) {
            System.err.println("Atom Map is not initialized.");
            return;
        }
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,1,Integer::sum);
                break;
            case BETA:
                atomMap.merge(AtomType.BETA,1,Integer::sum);
                break;
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,1,Integer::sum);
                break;
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,1,Integer::sum);
        }
    }
    public void addAmmo(Projectile projectile){
        if(atomMap==null) {
            System.err.println("Atom Map is not initialized.");
            return;
        }
        switch (projectile.getProjectileType().toString()){
            case AtomType.ALPHA_atom:
                shieldedAtomList.add(projectile);
                break;
            case AtomType.BETA_atom:
                shieldedAtomList.add(projectile);
                break;
            case AtomType.GAMMA_atom:
                shieldedAtomList.add(projectile);
                break;
            case AtomType.SIGMA_atom:
                shieldedAtomList.add(projectile);
            case PowerUpType.ALPHA_powerUp:
                powerUpMap.merge(PowerUpType.ALPHA,1,Integer::sum);
                break;
            case PowerUpType.BETA_powerUp:
                powerUpMap.merge(PowerUpType.BETA,1,Integer::sum);
                break;
            case PowerUpType.GAMMA_powerUp:
                powerUpMap.merge(PowerUpType.GAMMA,1,Integer::sum);
                break;
            case PowerUpType.SIGMA_powerUp:
                powerUpMap.merge(PowerUpType.SIGMA,1,Integer::sum);
        }
    }








    public void addAtom(AtomType atomType, int n){
        if(atomMap==null) {
            System.err.println("Atom Map is not initialized.");
            return;
        }
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,n,Integer::sum);
                break;
            case BETA:
                atomMap.merge(AtomType.BETA,n,Integer::sum);
                break;
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,n,Integer::sum);
                break;
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,n,Integer::sum);
        }
    }
    public void addPowerUp(PowerUpType powerUpType){
        if(powerUpMap==null) {
            System.err.println("PowerUp Map is not initialized.");
            return;
        }
        switch (powerUpType){
            case ALPHA:
                powerUpMap.merge(powerUpType.ALPHA,1,Integer::sum);
                break;
            case BETA:
                powerUpMap.merge(powerUpType.BETA,1,Integer::sum);
                break;
            case GAMMA:
               powerUpMap.merge(powerUpType.GAMMA,1,Integer::sum);
                break;
            case SIGMA:
                powerUpMap.merge(powerUpType.SIGMA,1,Integer::sum);
        }
    }
    public void addPowerUp(PowerUpType powerUpType, int n){
        if(powerUpMap==null) {
            System.err.println("PowerUp Map is not initialized.");
            return;
        }
        switch (powerUpType){
            case ALPHA:
                powerUpMap.merge(powerUpType.ALPHA,n,Integer::sum);
                break;
            case BETA:
                powerUpMap.merge(powerUpType.BETA,n,Integer::sum);
                break;
            case GAMMA:
                powerUpMap.merge(powerUpType.GAMMA,n,Integer::sum);
                break;
            case SIGMA:
                powerUpMap.merge(powerUpType.SIGMA,n,Integer::sum);
        }
    }
    public boolean removeAtom(AtomType atomType){
        if(atomMap==null) {
            System.err.println("Atom Map is not initialized.");
            return false;
        }
        if(atomMap.get(atomType)-1 < 0)return false;
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,-1,Integer::sum);
                break;
            case BETA:
                atomMap.merge(AtomType.BETA,-1,Integer::sum);
                break;
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,-1,Integer::sum);
                break;
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,-1,Integer::sum);
        }
        return true;
    }
    public boolean removeAtom(AtomType atomType, int n){
        if(atomMap==null) {
            System.err.println("Atom Map is not initialized.");
            return false;
        }
        if(atomMap.get(atomType)-n < 0)return false;
        switch (atomType){
            case ALPHA:
                atomMap.merge(AtomType.ALPHA,-n,Integer::sum);
                break;
            case BETA:
                atomMap.merge(AtomType.BETA,-n,Integer::sum);
                break;
            case GAMMA:
                atomMap.merge(AtomType.GAMMA,-n,Integer::sum);
                break;
            case SIGMA:
                atomMap.merge(AtomType.SIGMA,-n,Integer::sum);
        }
        return true;
    }
    public boolean removePowerUp(PowerUpType powerUpType){
        if(powerUpMap==null) {
            System.err.println("PowerUp Map is not initialized.");
            return false;
        }
        if(powerUpMap.get(powerUpType)-1 < 0)return false;
        switch (powerUpType.toString()){
            case PowerUpType.ALPHA_powerUp:
                powerUpMap.merge(powerUpType.ALPHA,-1,Integer::sum);
                break;
            case PowerUpType.BETA_powerUp:
                powerUpMap.merge(powerUpType.BETA,-1,Integer::sum);
                break;
            case PowerUpType.GAMMA_powerUp:
                powerUpMap.merge(powerUpType.GAMMA,-1,Integer::sum);
                break;
            case PowerUpType.SIGMA_powerUp:
                powerUpMap.merge(powerUpType.SIGMA,-1,Integer::sum);
        }
        return true;
    }

    //For save and Load Game functionalities

    public HashMap<AtomType, Integer> getAtomMap() {
        return atomMap;
    }

    public HashMap<PowerUpType, Integer> getPowerUpMap() {
        return powerUpMap;
    }
}
