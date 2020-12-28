package com.company.repository;

import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.UI.Objects.GameObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDB implements Database{

    MongoDatabase db;
    MongoCollection<Document> gameCollection;

    public MongoDB() {

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
            Integer> reactionBlockerAmount, int score, boolean isLinear, int time) {

        Document gameDb = new Document("username", username)
                .append("atomMap", atomMap.toString())
                .append("powerUpMap", powerUpMap.toString())
                .append("userPowerUpMap", userPowerUpMap.toString())
                .append("moleculeMap", moleculeMap.toString())
                .append("reactionBlockerAmount", reactionBlockerAmount.toString())
                .append("score", score)
                .append("isLinear", isLinear)
                .append("time", time);




        gameCollection.insertOne(gameDb);
    }

    @Override
    public void loadGame() {
         Document yoyo = gameCollection.find(new Document("name", "yoyo")).first();
        System.out.println(yoyo.toJson());
    }


}
