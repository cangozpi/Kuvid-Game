package com.company.UI.Objects.Observer;

import com.company.Domain.Models.Projectile.*;

import java.util.List;


public interface IGameListener {
    void positionChanged(List<Molecule> moleculeList, List<Atom> atomList, List<PowerUp> shotPowerUpList, List<ReactionBlocker> reactionBlockerList, List<PowerUp> powerUpList);

}
