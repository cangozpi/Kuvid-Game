package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.ReactionBlockerType;

public class ReactionBlocker extends Projectile {
    ReactionBlockerType reactionBlockerType;

    public ReactionBlocker(Coordinate coordinate, Velocity velocity, boolean isAmmo, ReactionBlockerType reactionBlockerType) {
        super(coordinate, velocity, isAmmo);
        this.reactionBlockerType = reactionBlockerType;
    }

    public ReactionBlockerType getReactionBlockerType() {
        return reactionBlockerType;
    }
}
