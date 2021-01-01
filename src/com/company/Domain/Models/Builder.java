package com.company.Domain.Models;

import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;

import java.util.HashMap;
import java.util.Map;

public class Builder {

    private GoodAlienFactory goodAlien;
    private BadAlienFactory badAlien;
    private GameFactory game;

    public Builder() {
    }

    public void buildGame(int gameWindowHeight, int gameWindowWidth, int difficulty, HashMap<AtomType, Integer> inventoryContents, boolean isLinear) {
        badAlien = BadAlienFactory.getInstance();
        goodAlien = GoodAlienFactory.getInstance();



        GameFactory game = GameFactory.getInstance();
        Inventory.getInstance().setAtomMap(inventoryContents);
        game.setDifficulty(difficulty);
        game.setFallSpeed(difficulty);
        game.setGameWindowHeight(gameWindowHeight);
        game.setGameWindowWidth(gameWindowWidth);


        GunFactory.getInstance();
        AtomSelector atomSelector = new AtomSelector();
        atomSelector.selectAtom();


        game.startGame();
        game.setLinear(isLinear);
    }


    public void createGoodAlien(HashMap<MoleculeType, Integer> moleculeAmount, HashMap<PowerUpType, Integer> powerUpAmount) {
        goodAlien.setPowerUpAmount(powerUpAmount);
        goodAlien.setMoleculeAmount(moleculeAmount);
    }

    public void createBadAlien(Map<ReactionBlockerType, Integer> reactionBlockerAmount) {
        badAlien.setReactionBlockerAmount(reactionBlockerAmount);
    }


}
