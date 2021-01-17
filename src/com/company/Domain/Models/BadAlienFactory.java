package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.ReactionBlocker;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.UI.Objects.GameWindowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BadAlienFactory {
    //Singleton Pattern
    private static BadAlienFactory instance;
    private GameFactory gameFactory;
    private int L;
    //variables
    Map<ReactionBlockerType, Integer> reactionBlockerAmount;
    Random random = new Random();
    private BadAlienFactory(){
        gameFactory = GameFactory.getInstance();
        L = gameFactory.getL();
    }

    public static BadAlienFactory getInstance(){
        instance =  instance == null ?  new BadAlienFactory() :  instance;
        return instance;
    }

    //decrement reactionBlockerAmount by one
    public ReactionBlocker sendReactionBlocker(){
        List<ReactionBlockerType> availableReactionBlockers = getAvailableReactionBlockers();
        if(!availableReactionBlockers.isEmpty()) {
            int reactionBlockerToShoot = random.nextInt(availableReactionBlockers.size());
            ReactionBlockerType type = availableReactionBlockers.get(reactionBlockerToShoot);
            Coordinate position = new Coordinate(random.nextInt(gameFactory.getGameWindowWidth()), 0);
            reactionBlockerAmount.put(type, reactionBlockerAmount.get(type) - 1);
            return new ReactionBlocker(position, new Velocity(270, gameFactory.getFallSpeed()),  type,false, L/4, L/4);
        }
        return null;
    }

    //setters
    public void setReactionBlockerAmount(Map<ReactionBlockerType, Integer> reactionBlockerAmount) {
        this.reactionBlockerAmount = reactionBlockerAmount;
    }

    public List<ReactionBlockerType> getAvailableReactionBlockers(){
        ArrayList<ReactionBlockerType> availableReactionBlockers = new ArrayList<>();
        for (Map.Entry<ReactionBlockerType, Integer> entry : reactionBlockerAmount.entrySet()) {
            if (entry.getValue() != 0) {
                availableReactionBlockers.add(entry.getKey());
            }
        }
        return availableReactionBlockers;
    }

    //for save functionality


    public Map<ReactionBlockerType, Integer> getReactionBlockerAmount() {
        return reactionBlockerAmount;
    }
}
