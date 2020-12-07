package com.company.Domain.Models;

import com.company.Enums.AtomType;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class AtomSelector {
    Inventory inventory = Inventory.getInstance();
    Gun gun = Gun.getInstance();
    private static AtomSelector atomSelector = null;

    private AtomSelector() {}
    public static AtomSelector getInstance() {
        if (atomSelector == null) atomSelector = new AtomSelector();
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
            gun.loadGunWithAtom(selectedAtom);
        }else System.err.println("There are no legal Atoms to use.");
        }
}
