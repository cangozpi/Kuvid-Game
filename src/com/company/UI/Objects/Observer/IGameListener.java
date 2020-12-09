package com.company.UI.Objects.Observer;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Models.Projectile.ReactionBlocker;

import java.util.List;


public interface IGameListener {
    void positionChanged(List<Molecule> moleculeList, List<Projectile> projectileList, List<ReactionBlocker> reactionBlockerList, List<PowerUp> powerUpList);

}
