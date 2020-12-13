package com.company.UI.Objects.Observer;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Utility.Coordinate;

import java.util.ArrayList;
import java.util.List;


public interface IGameListener {
    void positionChanged(ArrayList<Molecule> moleculeList, ArrayList<Atom> atomList, ArrayList<PowerUp> shotPowerUpList,
                         ArrayList<ReactionBlocker> reactionBlockerList, ArrayList<PowerUp> powerUpList, Coordinate gunPosition, int gunAngle);

}
