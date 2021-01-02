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
           case ALPHA:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.ALPHA, height, width);
           case ALPHA_L:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.ALPHA_L, height * 4, width * 12 / 10);
           case GAMMA:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.GAMMA, height, width);
           case BETA:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.BETA, height, width);
           case BETA_L:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.BETA_L, height, width);
           default:
               return new Molecule(coordinate, velocity, isAmmo, MoleculeType.SIGMA, height, width);
       }
   }
}
