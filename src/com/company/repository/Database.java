package com.company.repository;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.UI.Objects.GameObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Database {


    void saveGame(String username, HashMap<AtomType,Integer> atomMap, HashMap<PowerUpType,Integer> powerUpMap,
                  HashMap<PowerUpType,Integer> userPowerUpMap,
                  HashMap<MoleculeType,Integer> moleculeMap, Map<ReactionBlockerType,
                    Integer> reactionBlockerAmount, double score, boolean isLinear, int time);
    void loadGame();

}
