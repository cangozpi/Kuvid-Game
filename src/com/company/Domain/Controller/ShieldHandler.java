package com.company.Domain.Controller;

import com.company.Domain.Models.GunFactory;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Decorator.AtomDecorator;
import com.company.Enums.ShieldType;

public class ShieldHandler {
    GunFactory gun;
    public ShieldHandler(){


        gun = GunFactory.getInstance();
    }
    public void addShield(ShieldType shieldType){
            switch (shieldType){

                case ETA:
                   if(gun.getAmmo() instanceof Atom){
                       AtomDecorator ammo = (AtomDecorator) gun.getAmmo();

                       ammo.addShield(ShieldType.ETA);
                   }
                    break;
                case LOTA:
                    if(gun.getAmmo() instanceof Atom){
                        AtomDecorator ammo = (AtomDecorator) gun.getAmmo();
                        ammo.addShield(ShieldType.LOTA);
                    }
                    break;
                case THETA:
                    if(gun.getAmmo() instanceof Atom){
                        AtomDecorator ammo = (AtomDecorator) gun.getAmmo();
                        ammo.addShield(ShieldType.THETA);
                    }
                    break;
                case ZETA:
                    if(gun.getAmmo() instanceof Atom){
                        AtomDecorator ammo = (AtomDecorator) gun.getAmmo();
                        ammo.addShield(ShieldType.ZETA);
                    }
                    break;
            }
    }
}
