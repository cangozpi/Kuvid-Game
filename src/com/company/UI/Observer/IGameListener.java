package com.company.UI.Observer;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Utility.Coordinate;

import java.util.ArrayList;
import java.util.List;


public interface IGameListener {
    void positionChanged(ArrayList<Molecule> moleculeList, ArrayList<Projectile> projectileFromGunList,
                         ArrayList<ReactionBlocker> reactionBlockerList, ArrayList<PowerUp> powerUpList,
                         Coordinate gunPosition, int gunAngle,Projectile ammo);

}
