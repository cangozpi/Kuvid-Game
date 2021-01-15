package com.company.Domain.Controller;

import com.company.Domain.Models.GunFactory;

import com.company.Domain.Models.Inventory;
import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Decorator.AtomDecorator;
import com.company.Enums.ShieldType;

public class ShieldHandler {
    GunFactory gun;
    Inventory inventory;
    public ShieldHandler(){
        inventory = Inventory.getInstance();
        gun = GunFactory.getInstance();
    }
    public void addShield(ShieldType shieldType){
            switch (shieldType){

                case ETA:
                   if(gun.getAmmo() instanceof Atom){
                       AtomDecorator ammo = (AtomDecorator) gun.getAmmo();
                       inventory.removeShield(ShieldType.ETA);
                       ammo.addShield(ShieldType.ETA);
                   }
                    break;
                case LOTA:
                    if(gun.getAmmo() instanceof Atom){
                        AtomDecorator ammo = (AtomDecorator) gun.getAmmo();
                        inventory.removeShield(ShieldType.LOTA);
                        ammo.addShield(ShieldType.LOTA);
                    }
                    break;
                case THETA:
                    if(gun.getAmmo() instanceof Atom){
                        AtomDecorator ammo = (AtomDecorator) gun.getAmmo();
                        inventory.removeShield(ShieldType.THETA);
                        ammo.addShield(ShieldType.THETA);
                    }
                    break;
                case ZETA:
                    if(gun.getAmmo() instanceof Atom){
                        AtomDecorator ammo = (AtomDecorator) gun.getAmmo();
                        inventory.removeShield(ShieldType.ZETA);
                        ammo.addShield(ShieldType.ZETA);
                    }
                    break;
            }
    }
}
