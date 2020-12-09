package com.company.Domain.Observer;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Models.Projectile.ReactionBlocker;
import com.company.Domain.Utility.Coordinate;

import java.util.List;


public interface IGunListener {
    void gunMoved(Coordinate coord, int angle,Projectile ammo);

}
