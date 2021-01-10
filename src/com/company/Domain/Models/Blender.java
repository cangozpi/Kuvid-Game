package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Enums.AtomType;

import java.util.*;

public class Blender {
    Inventory inventory = Inventory.getInstance();
    private static Blender blender = null;

    private Blender() {}
    public static Blender getInstance() {
        if (blender == null) blender = new Blender();
        return blender;
    }
    public boolean blendAtoms(AtomType source, AtomType product, int sourceAmount){

        for (AtomType atomType: AtomType.values()){
            if(atomType == source) break;
            if(atomType == product){
                System.err.println("Cannot blend a higher atom type to produce a lower type.");
                return false;
            }
        }
        int totalSource = inventory.getAtomAmount(source);
        if(totalSource<sourceAmount) return false;
        switch (source){
            case ALPHA:
                switch (product){
                    case BETA:
                        sourceAmount-=sourceAmount%2;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/2);
                        break;
                    case GAMMA:
                        sourceAmount-=sourceAmount%3;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/3);
                        break;
                    case SIGMA:
                        sourceAmount-=sourceAmount%4;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/4);
                        break;
                }
                break;
            case BETA:
                switch (product){
                    case GAMMA:
                        sourceAmount-=sourceAmount%2;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/2);
                        break;
                    case SIGMA:
                        sourceAmount-=sourceAmount%3;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/3);
                        break;
                }
                break;
            case GAMMA:
                sourceAmount-=sourceAmount%2;
                inventory.removeAtom(source,sourceAmount);
                inventory.addAtom(product, sourceAmount/2);
        }
        return true;
    }
    /*
        @REQUIRES:Inventory instance/ atomType source:Beta,Gamma,Sigma / atomType product:Atom, Beta,Gamma / sourceAmount<0
        @MODIFIES:Inventory instance
        @EFFECTS: Creates multiple smaller AtomType product from larger AtomType source given a source atom amount
         and adds them to inventory.
     */
    public boolean breakAtoms(AtomType source, AtomType product, int sourceAmount){
        for (AtomType atomType: AtomType.values()){
            if(atomType == source){
                System.err.println("Cannot break a lower Atom type to produce a higher type.");
                return  false;
            }
            if(atomType == product)break;
        }
        int totalSource = inventory.getAtomAmount(source);
        if(totalSource<sourceAmount) return false;
        switch (source){
            case BETA:
                inventory.removeAtom(source,sourceAmount);
                inventory.addAtom(product, sourceAmount*2);
                break;
            case GAMMA:
                switch (product){
                    case BETA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*2);
                        break;
                    case ALPHA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*3);
                        break;
                }
                break;
            case SIGMA:
                switch (product){
                    case GAMMA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*2);
                        break;
                    case BETA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*3);
                        break;
                    case ALPHA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*4);
                }
        }
        return true;
    }

}

