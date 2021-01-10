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
    //REQUIRES: (source AtomType and product AtomType are not equal it returns true but does not blend)
    //MODIFIES: - not input but inventory instance
    //EFFECTS: returns true after removing the source’s atom amount in the
    //inventory by sourceAmount and adding the blender’s indicated amount for the desired source product exchange
    // to the product type’s atom amount in the inventory and turns false if sourceAmount is bigger than
    // the atom amount of the source in the inventory. Source Atom type must be a lower type than product atom.
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

