package com.company.repository;

import com.company.Domain.Models.BadAlienFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GoodAlienFactory;
import com.company.Domain.Models.Inventory;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDB implements Database{

    MongoDatabase db;
    MongoCollection<Document> gameCollection;
    GameFactory gameFactory;
    private HashMap<AtomType,Integer> atomMap;
    private HashMap<PowerUpType,Integer> powerUpMap;
    private HashMap<PowerUpType,Integer> userPowerUpMap;
    private HashMap<MoleculeType,Integer> moleculeMap;
    private Map<ReactionBlockerType, Integer> reactionBlockerAmount;

    public MongoDB() {

        gameFactory = GameFactory.getInstance();

        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://can:karel123@cluster0.dmdb7.mongodb.net/<dbname>?retryWrites=true&w=majority");

        db = mongoClient.getDatabase("kuvid");
        gameCollection = db.getCollection("game");

    }


    @Override
    public void saveGame(String username, HashMap<AtomType, Integer> atomMap,
                         HashMap<PowerUpType, Integer> powerUpMap, HashMap<PowerUpType,
            Integer> userPowerUpMap, HashMap<MoleculeType, Integer> moleculeMap, Map<ReactionBlockerType,
            Integer> reactionBlockerAmount, double score, boolean isLinear, int time) {

        Document gameDb = new Document("username", username)
                .append("atomMap", atomMap.toString())
                .append("powerUpMap", powerUpMap.toString())
                .append("userPowerUpMap", userPowerUpMap.toString())
                .append("moleculeMap", moleculeMap.toString())
                .append("reactionBlockerAmount", reactionBlockerAmount.toString())
                .append("score", score)
                .append("isLinear", isLinear)
                .append("time", time);

        gameCollection.deleteMany(new Document());
        gameCollection.insertOne(gameDb);
    }

    @Override
    public void loadGame() {
        Document savedGame = gameCollection.find(new Document("username", "Karel")).first();
        String atomMapRaw = (String) savedGame.get("atomMap");
        String powerUpMapRaw = (String) savedGame.get("powerUpMap");
        String userPowerUpMapRaw = (String) savedGame.get("userPowerUpMap");
        String moleculeMapRaw = (String) savedGame.get("moleculeMap");
        String reactionBlockerAmountRaw = (String) savedGame.get("reactionBlockerAmount");
        double score = (double)savedGame.get("score");
        boolean isLinear = (boolean) savedGame.get("isLinear");
        int time = (int) savedGame.get("time");


        gameFactory.setLinear(isLinear);
        gameFactory.setTime(time);
        gameFactory.setScore(score);



        try {
            atomMap = new HashMap<>();
            powerUpMap = new HashMap<>();
            userPowerUpMap = new HashMap<>();
            moleculeMap = new HashMap<>();
            reactionBlockerAmount = new HashMap<>();



            Arrays.stream(atomMapRaw.split(", ")).forEach((x)->{
                if (x.contains("GAMMA")) {
                    atomMap.put(AtomType.GAMMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("SIGMA")){
                    atomMap.put(AtomType.SIGMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("ALPHA")){
                    atomMap.put(AtomType.ALPHA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else
                    atomMap.put(AtomType.BETA, Integer.parseInt(x.replaceAll("\\D+","")));
            });
            Arrays.stream(powerUpMapRaw.split(", ")).forEach((x)->{
                if (x.contains("GAMMA")) {
                    powerUpMap.put(PowerUpType.GAMMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("SIGMA")){
                    powerUpMap.put(PowerUpType.SIGMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("ALPHA")){
                    powerUpMap.put(PowerUpType.ALPHA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else
                    powerUpMap.put(PowerUpType.BETA, Integer.parseInt(x.replaceAll("\\D+","")));
            });
            Arrays.stream(userPowerUpMapRaw.split(", ")).forEach((x)->{
                if (x.contains("GAMMA")) {
                    userPowerUpMap.put(PowerUpType.GAMMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("SIGMA")){
                    userPowerUpMap.put(PowerUpType.SIGMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("ALPHA")){
                    userPowerUpMap.put(PowerUpType.ALPHA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else {
                    userPowerUpMap.put(PowerUpType.BETA, Integer.parseInt(x.replaceAll("\\D+", "")));
                }
                });
            Arrays.stream(moleculeMapRaw.split(", ")).forEach((x)->{
                if (x.contains("GAMMA")) {
                    moleculeMap.put(MoleculeType.GAMMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("SIGMA")){
                    moleculeMap.put(MoleculeType.SIGMA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("ALPHA_L")){
                    moleculeMap.put(MoleculeType.ALPHA_L, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("BETA_L")){
                    moleculeMap.put(MoleculeType.BETA_L, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("ALPHA")){
                    moleculeMap.put(MoleculeType.ALPHA, Integer.parseInt(x.replaceAll("\\D+","")));
                }else
                    moleculeMap.put(MoleculeType.BETA, Integer.parseInt(x.replaceAll("\\D+","")));
            });
            Arrays.stream(reactionBlockerAmountRaw.split(", ")).forEach((x)->{
                if (x.contains("GAMMA")) {
                    reactionBlockerAmount.put(ReactionBlockerType.GAMMA_B, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("SIGMA")){
                    reactionBlockerAmount.put(ReactionBlockerType.SIGMA_B, Integer.parseInt(x.replaceAll("\\D+","")));
                }else if(x.contains("ALPHA")){
                    reactionBlockerAmount.put(ReactionBlockerType.ALPHA_B, Integer.parseInt(x.replaceAll("\\D+","")));
                }else
                    reactionBlockerAmount.put(ReactionBlockerType.BETA_B, Integer.parseInt(x.replaceAll("\\D+","")));
            });
            Inventory inventoryInstance = Inventory.getInstance();
            inventoryInstance.setAtomMap(atomMap);
            inventoryInstance.setPowerUpMap(powerUpMap);

            GoodAlienFactory goodAlienInstance = GoodAlienFactory.getInstance();
            goodAlienInstance.setPowerUpAmount(powerUpMap);
            goodAlienInstance.setMoleculeAmount(moleculeMap);

            BadAlienFactory badAlienInstance = BadAlienFactory.getInstance();
            badAlienInstance.setReactionBlockerAmount(reactionBlockerAmount);



        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
