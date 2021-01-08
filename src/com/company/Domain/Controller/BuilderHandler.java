package com.company.Domain.Controller;

import com.company.Domain.Models.Builder;
import com.company.Domain.Models.GameFactory;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;

import java.util.HashMap;
import java.util.Map;

public class BuilderHandler {
    private Builder builder;
    private GameFactory game;

    public BuilderHandler() {
        builder = new Builder();

    }

    public void buildGame(int gameWindowWidth, int gameWindowHeight, int difficulty, HashMap<AtomType, Integer> inventoryContents, HashMap<MoleculeType, Integer> moleculeAmounts, HashMap<PowerUpType, Integer> powerUpAmounts, Map<ReactionBlockerType, Integer> reactionBlockerAmounts, boolean isLinear, double L_ratio){
        builder.buildGame(gameWindowHeight, gameWindowWidth, difficulty, inventoryContents, isLinear, L_ratio);
        builder.createGoodAlien(moleculeAmounts, powerUpAmounts);
        builder.createBadAlien(reactionBlockerAmounts);

        //start the Domain GameFactory get that clock gameClock ticking
        this.game = GameFactory.getInstance();
        game.startGame();

    }


}
