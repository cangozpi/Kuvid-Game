package com.company.Domain.Models.Repository;

import com.company.Domain.Controller.MenuHandler;
import com.company.Domain.Models.BadAlienFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GoodAlienFactory;
import com.company.Domain.Models.Inventory;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.repository.DatabaseAdapter;
import com.company.repository.LocalDB;
import com.company.repository.MongoDB;

import java.util.HashMap;
import java.util.Map;

public class DataTransfer {
    //Save Game parameters
    private HashMap<AtomType,Integer> atomMap;
    private HashMap<PowerUpType,Integer> powerUpMap;
    private HashMap<MoleculeType,Integer> moleculeMap;
    private Map<ReactionBlockerType, Integer> reactionBlockerAmount;
    private int score;
    private boolean isLinear;
    private int time;
    HashMap<PowerUpType, Integer> goodAlienPowerUpMap;
    private DatabaseAdapter databaseAdapter;

    public DataTransfer(){

        //databaseAdapter = new DatabaseAdapter(new LocalDB());
        databaseAdapter = new DatabaseAdapter(new MongoDB());
        //saveGame parameters
        Inventory inventoryInstance = Inventory.getInstance();
        GameFactory gameFactory = GameFactory.getInstance();
        atomMap = inventoryInstance.getAtomMap();
        powerUpMap = inventoryInstance.getPowerUpMap();

        GoodAlienFactory goodAlienInstance = GoodAlienFactory.getInstance();
        goodAlienPowerUpMap = goodAlienInstance.getPowerUpAmounts();
        moleculeMap = goodAlienInstance.getMoleculeAmounts();

        BadAlienFactory badAlienInstance = BadAlienFactory.getInstance();
        reactionBlockerAmount = badAlienInstance.getReactionBlockerAmount();

        score = gameFactory.getScore();
        time = gameFactory.getTime();

        isLinear = gameFactory.isLinear();
    }


    public void saveGame() {
        databaseAdapter.saveGame("Karel",atomMap, goodAlienPowerUpMap, powerUpMap, moleculeMap, reactionBlockerAmount, score, isLinear, time);
    }
    public void loadGame(){
        databaseAdapter.loadGame();
    }


}
