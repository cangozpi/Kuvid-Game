package com.company.Domain.Models;

import com.company.Enums.*;

import java.util.HashMap;
import java.util.Map;

public class Builder {

    private GoodAlienFactory goodAlien;
    private BadAlienFactory badAlien;
    private GameFactory game;

    public Builder() {

    }

    public void buildGame(int gameWindowHeight, int gameWindowWidth, int difficulty, HashMap<AtomType, Integer> inventoryContents, HashMap<ShieldType, Integer> shieldAmount, boolean isLinear, double L_ratio) {
        badAlien = BadAlienFactory.getInstance();
        goodAlien = GoodAlienFactory.getInstance();



        GameFactory game = GameFactory.getInstance();
        Inventory.getInstance().setAtomMap(inventoryContents);
        Inventory.getInstance().setShieldMap(shieldAmount);
        game.setDifficulty(difficulty);
        game.setFallSpeed(difficulty);
        game.setGameWindowHeight(gameWindowHeight);
        game.setGameWindowWidth(gameWindowWidth);
        game.setL(L_ratio);


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
