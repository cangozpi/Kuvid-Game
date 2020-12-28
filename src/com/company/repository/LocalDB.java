package com.company.repository;

import com.company.Domain.Models.BadAlienFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GoodAlienFactory;
import com.company.Domain.Models.Inventory;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.UI.Objects.GameObject;
import com.company.UI.Objects.GameWindowFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocalDB implements Database {

    private String username;
    private HashMap<AtomType,Integer> atomMap;
    private HashMap<PowerUpType,Integer> powerUpMap;
    private HashMap<PowerUpType,Integer> userPowerUpMap;
    private HashMap<MoleculeType,Integer> moleculeMap;
    private Map<ReactionBlockerType, Integer> reactionBlockerAmount;
    private int score;
    private boolean isLinear;
    private int time;
    private ArrayList<GameObject> objectList;

    @Override
    public void saveGame(String username, HashMap<AtomType, Integer> atomMap, HashMap<PowerUpType, Integer> powerUpMap, HashMap<PowerUpType, Integer> userPowerUpMap, HashMap<MoleculeType, Integer> moleculeMap, Map<ReactionBlockerType, Integer> reactionBlockerAmount, int score, boolean isLinear, int time, ArrayList<GameObject> objectList) {
        //Types of atoms and molecules
        JSONObject savedGame = new JSONObject();

        //reformat moleculeMap
        //moleculeMap.values().stream().forEach((x) -> {});

        //initialize JSON objects
        //domain part
        savedGame.put("username",username);
        savedGame.put("atomMap", atomMap);
        savedGame.put("powerUpMap", powerUpMap);
        savedGame.put("userPowerUpMap", userPowerUpMap);
        savedGame.put("moleculeMap", moleculeMap);
        savedGame.put("reactionBlockerAmount", reactionBlockerAmount);
        savedGame.put("score", score);
        savedGame.put("isLinear", isLinear);
        savedGame.put("time",time);

        //UI part
        GameWindowFactory gameWindowInstance = GameWindowFactory.getInstance();
        JSONObject objectListJSON = new JSONObject();
        savedGame.put("objectList", gameWindowInstance.getObjectList().toString());


        //Write JSON file
        try (FileWriter file = new FileWriter("savedGame.json")) {

            file.write(savedGame.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadGame() {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("savedGame.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            parseSavedGame((JSONObject)obj);

            //saveGame parameters
            Inventory inventoryInstance = Inventory.getInstance();
            GameFactory gameFactory = GameFactory.getInstance();
            inventoryInstance.setAtomMap(atomMap);
            inventoryInstance.setPowerUpMap(powerUpMap);

            GoodAlienFactory goodAlienInstance = GoodAlienFactory.getInstance();
            goodAlienInstance.setPowerUpAmount(powerUpMap);
            goodAlienInstance.setMoleculeAmount(moleculeMap);

            BadAlienFactory badAlienInstance = BadAlienFactory.getInstance();
            badAlienInstance.setReactionBlockerAmount(reactionBlockerAmount);

            gameFactory.setScore(score);
            gameFactory.setTime(time);
            gameFactory.setLinear(isLinear);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseSavedGame(JSONObject savedGame) {

        //convert JSONObject back to JSON
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            atomMap = new HashMap<>();
            powerUpMap = new HashMap<>();
            userPowerUpMap = new HashMap<>();
            moleculeMap = new HashMap<>();
            reactionBlockerAmount = new HashMap<>();

            //Convert back to JSON from local file
            //username = mapper.readValue(savedGame.get("username").toString(), String.class);
            HashMap<String, Integer> atomMapRaw = mapper.readValue(savedGame.get("atomMap").toString(), HashMap.class);
            HashMap<String, Integer> powerUpMapRaw = mapper.readValue(savedGame.get("powerUpMap").toString(), HashMap.class);
            HashMap<String, Integer> userPowerUpMapRaw =  mapper.readValue(savedGame.get("userPowerUpMap").toString(), HashMap.class);
            HashMap<String, Integer> moleculeMapRaw =  mapper.readValue(savedGame.get("moleculeMap").toString(), HashMap.class);
            HashMap<String, Integer> reactionBlockerAmountRaw = mapper.readValue(savedGame.get("reactionBlockerAmount").toString(), HashMap.class);
            score = mapper.readValue(savedGame.get("score").toString(), Integer.class);
            isLinear =  mapper.readValue(savedGame.get("isLinear").toString(), Boolean.class);
            time = mapper.readValue(savedGame.get("time").toString(), Integer.class);
            objectList = mapper.readValue(savedGame.get("objectList").toString(), ObjectListJSON.class);
            //GameObject[] emikObjectList = mapper.readValue(savedGame.get("objectList").toString(), GameObject[].class);
            System.out.println(objectList);

            atomMapRaw.forEach((key,value) -> {
                if (key.contains("GAMMA")) {
                    atomMap.put(AtomType.GAMMA, value);
                }else if(key.contains("BETA")){
                    atomMap.put(AtomType.BETA, value);
                } else if(key.contains("SIGMA")){
                    atomMap.put(AtomType.SIGMA, value);
                } else{
                    atomMap.put(AtomType.ALPHA, value);
                }
            });
            powerUpMapRaw.forEach((key,value) -> {
                if (key.contains("GAMMA")) {
                    powerUpMap.put(PowerUpType.GAMMA,value);
                }else if(key.contains("BETA")){
                    powerUpMap.put(PowerUpType.BETA, value);
                }else if(key.contains("SIGMA")){
                    powerUpMap.put(PowerUpType.SIGMA, value);
                } else{
                    powerUpMap.put(PowerUpType.ALPHA, value);
                }
            });

            userPowerUpMapRaw.forEach((key,value) -> {
                if (key.contains("GAMMA")) {
                    userPowerUpMap.put(PowerUpType.GAMMA, value);
                }else if(key.contains("BETA")){
                    userPowerUpMap.put(PowerUpType.BETA, value);
                } else if(key.contains("SIGMA")){
                    userPowerUpMap.put(PowerUpType.SIGMA, value);
                } else{
                    userPowerUpMap.put(PowerUpType.ALPHA, value);
                }
            });

            moleculeMapRaw.forEach((key,value) -> {
                if (key.contains("GAMMA")) {
                    moleculeMap.put(MoleculeType.GAMMA, value);
                }else if(key.contains("BETA_1")){
                    moleculeMap.put(MoleculeType.BETA_1, value);
                } else if(key.contains("ALPHA_1")){
                    moleculeMap.put(MoleculeType.ALPHA_1, value);
                } else if(key.contains("BETA_2")){
                    moleculeMap.put(MoleculeType.BETA_2, value);
                } else if(key.contains("ALPHA_2")){
                    moleculeMap.put(MoleculeType.ALPHA_2, value);
                } else{
                    moleculeMap.put(MoleculeType.SIGMA, value);
                }
            });

            reactionBlockerAmountRaw.forEach((key,value) -> {
                if (key.contains("GAMMA")) {
                    reactionBlockerAmount.put(ReactionBlockerType.GAMMA_B, value);
                }else if(key.contains("BETA")){
                    reactionBlockerAmount.put(ReactionBlockerType.BETA_B, value);
                } else if(key.contains("SIGMA")){
                    reactionBlockerAmount.put(ReactionBlockerType.SIGMA_B, value);
                } else{
                    reactionBlockerAmount.put(ReactionBlockerType.ALPHA_B, value);
                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
