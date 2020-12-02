package com.company.Domain.Models;

import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;

import java.util.HashMap;

public class Builder {


    public void buildGame(int gameWindowHeight, int gameWindowWidth, int difficulty, HashMap<AtomType, Integer> inventoryContents) {

        // Game instance
        GameFactory game = GameFactory.getInstance();
        // Setting initial values
        game.setDifficulty(difficulty);
        game.setGameWindowHeight(gameWindowHeight);
        game.setGameWindowWidth(gameWindowWidth);
    }


    public void buildGoodAlien(HashMap<MoleculeType, Integer> moleculeAmounts, HashMap<PowerUpType, Integer> powerUpAmounts) {

    }

    public void buildBadAlien(HashMap<ReactionBlockerType, Integer> reactionBlockerAmounts) {

    }


}
