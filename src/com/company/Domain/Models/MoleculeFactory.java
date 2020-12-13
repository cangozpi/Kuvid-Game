package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;

public class MoleculeFactory {
    public MoleculeFactory(){

    }

   public Molecule getInstance(Coordinate coordinate, Velocity velocity, boolean isAmmo, MoleculeType moleculeType, int height, int width){
       switch (moleculeType){
           case ALPHA_1:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.ALPHA_1, height, width);
           case ALPHA_2:
               return new Molecule(coordinate, velocity, isAmmo,   MoleculeType.ALPHA_2, height, width);
           case GAMMA:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.GAMMA, height, width);
           case BETA_1:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.BETA_1, height, width);
           case BETA_2:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.BETA_2, height, width);
           default:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.SIGMA, height, width);
       }
   }
}
