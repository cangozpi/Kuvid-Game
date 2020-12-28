package com.company.repository;

import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;

import java.util.HashMap;
import java.util.Map;

public class DatabaseAdapter implements Database{

    private Database database;
    public DatabaseAdapter(Database database) {
        this.database = database;
    }


    @Override
    public void saveGame(String username, HashMap<AtomType, Integer> atomMap, HashMap<PowerUpType, Integer> powerUpMap, HashMap<PowerUpType, Integer> userPowerUpMap, HashMap<MoleculeType, Integer> moleculeMap, Map<ReactionBlockerType, Integer> reactionBlockerAmount, int score, boolean isLinear, int time) {
        database.saveGame(username, atomMap, powerUpMap, userPowerUpMap, moleculeMap, reactionBlockerAmount, score,isLinear, time);
    }

    @Override
    public void loadGame() {
        database.loadGame();
    }
}
