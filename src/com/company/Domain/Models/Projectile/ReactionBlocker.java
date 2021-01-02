package com.company.Domain.Models.Projectile;

import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.MovementStrategyFactory;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Path;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.ReactionBlockerType;

public class ReactionBlocker extends Projectile {
    ReactionBlockerType reactionBlockerType;
    private Path path;
    public ReactionBlocker(Coordinate coordinate, Velocity velocity, boolean isAmmo, ReactionBlockerType reactionBlockerType, int height, int width) {
        super(coordinate, velocity, isAmmo, height, width);
        this.reactionBlockerType = reactionBlockerType;
        this.path = new Path(coordinate, velocity, 30);
    }

    public ReactionBlockerType getReactionBlockerType() {
        return reactionBlockerType;
    }

    @Override
    public void move() {
        MovementStrategyFactory strategyFactory = new MovementStrategyFactory();

        this.path = strategyFactory.getMovementStrategy(getReactionBlockerType(),getCoordinate()).newPath(this.path);
        setCoordinate(path.getCoordinate());
    }

}
