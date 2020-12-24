package com.company.Domain.Models;

import com.company.Enums.AtomType;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class AtomSelectorFactory {
    Inventory inventory = Inventory.getInstance();
    GunFactory gun = GunFactory.getInstance();
    private static AtomSelectorFactory atomSelector = null;

    private AtomSelectorFactory() {}
    public static AtomSelectorFactory getInstance() {
        if (atomSelector == null) atomSelector = new AtomSelectorFactory();
        return atomSelector;
    }
    public void selectAtom(){


        ArrayList<AtomType> legalAtomList = new ArrayList<>();
        for (AtomType atomType: AtomType.values()){
            if(inventory.getAtomAmount(atomType)>0)legalAtomList.add(atomType);
            }
        if(!legalAtomList.isEmpty()) {
            AtomType selectedAtom = legalAtomList.get((int) (Math.random() * (legalAtomList.size())));
            inventory.removeAtom(selectedAtom);
            gun.loadGun(selectedAtom);
        }else System.err.println("There are no legal Atoms to use.");
        }
}
