package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

import java.util.Random;

public class BetaDecorator extends AtomDecorator{

    private Random rand = new Random();
    private double stabilityConstant;
    private double protons;
    private double efficiency;
    private double neutrons;

    public BetaDecorator(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width, Atom atom) {
        super(coordinate, velocity, atomType, isAmmo, height, width, atom);

        stabilityConstant = 0.9;
        protons = 16;

        //randomly have neutrons in the range of 15, 16, 17, 18, 21
        int randNum = rand.nextInt(5);
        switch (randNum){
            case 0:
                neutrons = 15;
                break;
            case 1:
                neutrons = 16;
                break;
            case 2:
                neutrons = 17;
                break;
            case 3:
                neutrons = 18;
                break;
            default://for case 4:
                neutrons = 21;
                break;
        }

        //Efficiency = Beta stability constant - ( 0.5 * | # of neutrons - # of protons | /  # of protons)
        efficiency = stabilityConstant - ((0.5 * (neutrons - protons)) / protons) ;

    }

    @Override
    public double getStabilityConstant() {
        return getStabilityConstant();
    }

    @Override
    public double getEfficiency() {
        return efficiency;
    }

    @Override
    public double getProtons() {
        return protons;
    }

    @Override
    public double getNeutrons() {
        return neutrons;
    }
}
