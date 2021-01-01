package com.company.Domain.Models;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

import static com.company.UI.Objects.GameWindowFactory.L;

public class AtomSelector {
    Inventory inventory = Inventory.getInstance();
    GunFactory gun = GunFactory.getInstance();


    public AtomSelector() {}


    public void selectAtom(){
        int ALPHA_amount = inventory.getAtomAmount(AtomType.ALPHA);
        int BETA_amount = inventory.getAtomAmount(AtomType.BETA);
        int GAMMA_amount = inventory.getAtomAmount(AtomType.GAMMA);
        int SIGMA_amount = inventory.getAtomAmount(AtomType.SIGMA);
        int unshielded_amount = ALPHA_amount + BETA_amount + GAMMA_amount + SIGMA_amount;
        int shielded_amount =  inventory.getShieldedAtomAmount();
        int total_amount = unshielded_amount + shielded_amount;
        if(total_amount != 0) {
            int randomNumber = (int) (Math.random() * (total_amount - 1));
            AtomFactory atomFactory = new AtomFactory();


            if(randomNumber < shielded_amount){
                gun.loadGun(inventory.getShieldedAtom(randomNumber));
            }else if(randomNumber < ALPHA_amount + shielded_amount){
                inventory.removeAtom(AtomType.ALPHA);
                gun.loadGun(atomFactory.getInstance(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,L/10,L/10));
            }else if(randomNumber < ALPHA_amount + BETA_amount + shielded_amount) {
                inventory.removeAtom(AtomType.BETA);
                gun.loadGun(atomFactory.getInstance(new Coordinate(0,0), new Velocity(0,0), AtomType.BETA, true,L/10,L/10));
            }else if(randomNumber < ALPHA_amount + BETA_amount + GAMMA_amount + shielded_amount) {
                inventory.removeAtom(AtomType.GAMMA);
                gun.loadGun(atomFactory.getInstance(new Coordinate(0,0), new Velocity(0,0), AtomType.GAMMA, true,L/10,L/10));
            }else {
                inventory.removeAtom(AtomType.SIGMA);
                gun.loadGun(atomFactory.getInstance(new Coordinate(0,0), new Velocity(0,0), AtomType.SIGMA, true,L/10,L/10));
            }
        } else System.err.println("There are no legal Atoms to use."); }


}
