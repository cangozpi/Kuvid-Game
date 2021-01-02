package com.company.UI;

import com.company.Domain.Controller.BlenderHandler;
import com.company.Domain.Controller.MenuHandler;
import com.company.Domain.Models.BadAlienFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GoodAlienFactory;
import com.company.Domain.Models.Inventory;
import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.UI.Objects.BackgroundObject;
import com.company.UI.Objects.GameObject;
import com.company.UI.Objects.GameWindowFactory;
import com.company.UI.Objects.GunObject;
import com.company.Utils.CenterWindow;
import com.company.repository.DatabaseAdapter;
import com.company.repository.LocalDB;
import com.company.repository.MongoDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuWindowFactory extends JFrame implements KeyListener {

    public static int windowWidth = 720;
    public static int windowHeight = 360;
    private static MenuWindowFactory factoryInstance;
    private MenuHandler menuHandler;
    private Inventory inventory;
    private JPanel[][] panelHolder;

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


    //singleton instance
    private static MenuWindowFactory instance;

    private MenuWindowFactory() {
        menuHandler = new MenuHandler();
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

    public static MenuWindowFactory getInstance(){
        if(instance == null){
            instance = new MenuWindowFactory();
            return instance;
        } else{
            return instance;
        }
    }

    public void render() {
        //factoryInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowWidth, windowHeight);
        this.setTitle("KUVid Menu");
        this.setLayout(new FlowLayout());
        this.setBackground(Color.DARK_GRAY);

        CenterWindow.centerWindow(this);
        JPanel inputPanel = new JPanel();
        int rows = 1;
        int columns = 1;

        inputPanel.setLayout(new GridLayout(rows, columns));

        panelHolder = new JPanel[rows][columns];

        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < columns; n++) {
                panelHolder[m][n] = new JPanel();
                inputPanel.add(panelHolder[m][n]);
            }
        }
        panelHolder[0][0].add(new JLabel("Game Paused"));
        this.getContentPane().removeAll();
        this.getContentPane().add(inputPanel);
        this.setVisible(true);

        //add keyListener to the JFrame
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 82){ //R
            getInstance().dispose();
            GameFactory.getInstance().resumeGame();
        }

        else if(e.getKeyCode() == 83){ //S
            databaseAdapter.saveGame("Karel",atomMap, goodAlienPowerUpMap, powerUpMap, moleculeMap, reactionBlockerAmount, score, isLinear, time);
        }

        else if(e.getKeyCode() == 76){ //L
            databaseAdapter.loadGame();
            getInstance().dispose();
            GameFactory.getInstance().resumeGame();
            //reset game UI
            GameFactory.getInstance().resetObjectLists();

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
