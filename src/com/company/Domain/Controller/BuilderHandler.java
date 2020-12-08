package com.company.Domain.Controller;

import com.company.Domain.Models.Builder;
import com.company.Domain.Models.GameFactory;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;

import java.util.Map;

public class BuilderHandler {
    private Builder builder;

    public BuilderHandler(Builder builder) {
        this.builder = builder;
    }

    public void buildGame(int gameWindowWidth, int gameWindowHeight, int difficulty, Map<AtomType, Integer> inventoryContents, Map<MoleculeType, Integer> moleculeAmounts, Map<PowerUpType, Integer> powerUpAmounts, Map<ReactionBlockerType, Integer> reactionBlockerAmounts){
        builder.buildGame(gameWindowHeight, gameWindowWidth, difficulty, inventoryContents);
        builder.createGoodAlien(moleculeAmounts, powerUpAmounts);
        builder.createBadAlien(reactionBlockerAmounts);
    }

}
