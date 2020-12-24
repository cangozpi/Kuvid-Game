package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.*;


import com.company.Domain.Utility.Coordinate;
import com.company.UI.Objects.GameWindowFactory;
import com.company.UI.Observer.GameObserver;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class GameFactory extends GameObserver  {
    private boolean isRunning;

    private static GameFactory instance;
    private int gameWindowHeight;
    private int gameWindowWidth;
    private int difficulty;
    public int L;


    private Coordinate gunPosition;
    private int gunAngle;
    private ArrayList<Projectile> projectileFromGunList = new ArrayList<>();
    private ArrayList<PowerUp> shotPowerUpList = new ArrayList<>();
    private ArrayList<PowerUp> powerUpList = new ArrayList<>();
    private ArrayList<ReactionBlocker> reactionBlockerList = new ArrayList<>();
    private ArrayList<Molecule> moleculeList = new ArrayList<>();
    private Projectile ammo;
    private GameWindowFactory factory;
    private int fallSpeed;
    private Timer gameClock;

    private GameFactory(){
        super(); //necessary for initializing Observer

    }

    public static GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();



        }
        return instance;
    }




    public void startGame(){
        L = gameWindowHeight / 10;
        setGunPosition(new Coordinate(((gameWindowWidth/2) - L / 4),
                gameWindowHeight -  L));
        setGunAngle(90);
        gameLoop();
    }

    //gameLoop() handles both game clock and alien clock
    public void gameLoop() {

        boolean flag = false;

        //16.68ms for 60FPS
            gameClock =  new Timer(16, new ActionListener() { // checks for cat icons collusion

            @Override
            public void actionPerformed(ActionEvent e) {

                updatePositions();

            }
        });

        gameClock.start();

        //handles alien actions
        Timer alienClock =  new Timer(5000, new ActionListener() { // checks for cat icons collusion

            @Override
            public void actionPerformed(ActionEvent e) {
                GoodAlienFactory alienFactoryInstance = GoodAlienFactory.getInstance();
                Molecule moleculeToSend = alienFactoryInstance.sendMolecule();
                if (moleculeToSend!=null)
                    insertMolecule(moleculeToSend);
            }
        });
        alienClock.start();


    }

    public void pauseGame(){
        gameClock.stop();
    }

    public void resumeGame(){
        gameClock.start();
    }

    public void insertProjectileFromGun(Projectile ammo){    //projectiles loaded in the gun and shot by the gun


        projectileFromGunList.add(ammo);

    }

    public void insertMolecule(Molecule molecule){         //molecules from good alien

        moleculeList.add(molecule);
    }

    public void insertPowerUp(PowerUp powerUp){           //powerups from good alien

        powerUpList.add(powerUp);
    }

    public void insertReactionBlocker(ReactionBlocker reactionBlocker){    //reaction blockers from bad alien

       reactionBlockerList.add(reactionBlocker);
    }

    public void updatePositions(){
        if (!moleculeList.isEmpty()){
            for (Molecule molecule : moleculeList) {
                molecule.move();
            }
        }
        if (!projectileFromGunList.isEmpty()){                //projectiles shot by the gun
            for (Projectile projectileFromGun : projectileFromGunList) {
                projectileFromGun.move();
            }
        }


        if (!reactionBlockerList.isEmpty()) {
            for (ReactionBlocker reactionBlocker : reactionBlockerList) {
                reactionBlocker.move();
            }
        }
        if (!powerUpList.isEmpty()){
            for (PowerUp powerUp : powerUpList) {
                powerUp.move();
            }
        }   

        checkCollisions();




        GameFactory.super.positionUpdateEvent(moleculeList, projectileFromGunList,  reactionBlockerList,powerUpList, gunPosition, gunAngle, ammo);
    }

    public void checkCollisions(){
        // iterates over reaction blocker list
        //

    }


    public void moveGun(Coordinate coord, int angle, Projectile ammo){

        setAmmo(ammo);
        this.gunPosition = coord;
        this.gunAngle = angle;
    }

    //getter setters

    public Coordinate getGunPosition() {
        return gunPosition;
    }

    public void setGunPosition(Coordinate gunPosition) {
        this.gunPosition = gunPosition;
    }

    public int getGunAngle() {
        return gunAngle;
    }

    public void setGunAngle(int gunAngle) {
        this.gunAngle = gunAngle;
    }

    public int getGameWindowHeight() {
        return gameWindowHeight;
    }

    public int getGameWindowWidth() {
        return gameWindowWidth;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public ArrayList<Projectile> getProjectileFromGunList() {
        return projectileFromGunList;
    }

    public void setProjectileFromGunList(ArrayList<Projectile> projectileFromGunList) {
        this.projectileFromGunList = projectileFromGunList;
    }

    public void setGameWindowHeight(int gameWindowHeight) {
        this.gameWindowHeight = gameWindowHeight;
    }

    public void setGameWindowWidth(int gameWindowWidth) {
        this.gameWindowWidth = gameWindowWidth;
    }

    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public void setFallSpeed(int difficulty) {
        if (difficulty == 1)
            this.fallSpeed = 1;
        else if(difficulty == 2)
            this.fallSpeed = 2;
        else if(difficulty == 3)
            this.fallSpeed = 4;
    }

    public int getFallSpeed(){
        return fallSpeed;
    }

    public int getL() { return L; }

    public ArrayList<PowerUp> getPowerUpList() { return powerUpList; }

    public ArrayList<ReactionBlocker> getReactionBlockerList() { return reactionBlockerList; }

    public ArrayList<Molecule> getMoleculeList() { return moleculeList; }

    public Projectile getAmmo() {
        return ammo;
    }

    public void setAmmo(Projectile ammo) {
        this.ammo = ammo;
    }

    public static void setInstance(GameFactory instance) { GameFactory.instance = instance; }

}
