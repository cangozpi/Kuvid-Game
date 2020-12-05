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

        for (AtomType atomType: EnumSet.allOf(AtomType.class)){
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
                    case GAMMA:
                        sourceAmount-=sourceAmount%3;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/3);
                    case SIGMA:
                        sourceAmount-=sourceAmount%4;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/4);
                }
            case BETA:
                switch (product){
                    case GAMMA:
                        sourceAmount-=sourceAmount%2;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/2);
                    case SIGMA:
                        sourceAmount-=sourceAmount%3;
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount/3);
                }
            case GAMMA:
                sourceAmount-=sourceAmount%2;
                inventory.removeAtom(source,sourceAmount);
                inventory.addAtom(product, sourceAmount/2);
        }
return true;
    }
    public boolean breakAtoms(AtomType source, AtomType product, int sourceAmount){
        for (AtomType atomType: EnumSet.allOf(AtomType.class)){
            if(atomType == source){
                System.err.println("Cannot break a lower Atom type to produce a higher type.");
                return  false;
            }
            if(atomType == product)break;
        }

        switch (source){
            case BETA:
                inventory.removeAtom(source,sourceAmount);
                inventory.addAtom(product, sourceAmount*2);
            case GAMMA:
                switch (product){
                    case BETA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*2);
                    case ALPHA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*3);
                }
            case SIGMA:
                switch (product){
                    case GAMMA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*2);
                    case BETA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*3);
                    case ALPHA:
                        inventory.removeAtom(source,sourceAmount);
                        inventory.addAtom(product, sourceAmount*4);
                }
        }
        return true;
    }

}

