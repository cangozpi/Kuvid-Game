package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Utility.Coordinate;

import com.company.UI.Observer.GameObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFactory extends GameObserver  {


    private static GameFactory instance;
    private int gameWindowHeight;
    private int gameWindowWidth;
    private int difficulty;
    public int L;


    private Coordinate gunPosition;
    private int gunAngle = 0;
    private ArrayList<Projectile> projectileFromGunList = new ArrayList<>();

    private ArrayList<PowerUp> powerUpList = new ArrayList<>();
    private ArrayList<ReactionBlocker> reactionBlockerList = new ArrayList<>();
    private ArrayList<Molecule> moleculeList = new ArrayList<>();
    private Projectile ammo;

    private int fallSpeed;


    private boolean isLinear;
    private int score;
    private int time;

    private GameFactory(){
        super(); //necessary for initializing Observer
        time = 0;
        score = 0; //TODO: implement score on collision and such

    }

    public static GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();

        }
        return instance;
    }




    public void startGame(){
        gameLoop();
    }

    //gameLoop() handles both game clock and alien clock
    public void gameLoop() {
        gameClock.start();
        goodAlienClock.start();
        badAlienClock.start();
    }

    Timer badAlienClock =  new Timer(5000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            BadAlienFactory badAlienFactory = BadAlienFactory.getInstance();
            ReactionBlocker reactionBlocker = badAlienFactory.sendReactionBlocker();
            if (reactionBlocker!=null)
                insertReactionBlocker(reactionBlocker);
        }
    });

    Timer goodAlienClock =  new Timer(3000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GoodAlienFactory goodAlienFactory = GoodAlienFactory.getInstance();
            PowerUp powerUpToSend = goodAlienFactory.sendPowerUp();
            Molecule moleculeToSend = goodAlienFactory.sendMolecule();
            if (moleculeToSend!=null)
                insertMolecule(moleculeToSend);
            if (powerUpToSend!=null)
                insertPowerUp(powerUpToSend);
        }
    });

    //16.68ms for 60FPS
    Timer gameClock =  new Timer(33, new ActionListener() { // checks for cat icons collusion

        @Override
        public void actionPerformed(ActionEvent e) {
            time++;
            updatePositions();
        }
    });



    public void pauseGame(){
        gameClock.stop();
        goodAlienClock.stop();
        badAlienClock.stop();
    }

    public void resumeGame(){

        gameClock.start();
        goodAlienClock.start();
        badAlienClock.start();
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
    public boolean hasCollided(Projectile projectile1, Projectile projectile2){
        if ( (projectile1.getYCoordinate() <= projectile2.getYCoordinate() + projectile2.getHeight() &
                projectile2.getYCoordinate() + projectile2.getHeight() <= projectile1.getYCoordinate() + projectile1.getHeight()) |
                (projectile1.getYCoordinate() <= projectile2.getYCoordinate() &
                        projectile2.getYCoordinate() <= projectile1.getYCoordinate() + projectile1.getHeight())){             // gun in explosion radius
            if ((projectile1.getXCoordinate() <= projectile2.getXCoordinate() + projectile2.getWidth() &
                    projectile2.getXCoordinate() + projectile2.getWidth() <= projectile1.getXCoordinate() + projectile1.getWidth()) |
                    (projectile1.getXCoordinate() <= projectile2.getXCoordinate() &
                            projectile2.getXCoordinate() <= projectile1.getXCoordinate() + projectile1.getWidth())){
                return true;
            }
        }
        return false;
    }

    public boolean hasCollidedReactionBlocker(Coordinate coord1,  int width1,  int height1, Coordinate coord2, int width2, int height2){
        if ( (coord1.getYCoordinate() <= coord2.getYCoordinate() + height2 &
                coord2.getYCoordinate() + height2 <= coord1.getYCoordinate() + height1) |
                (coord1.getYCoordinate() <= coord2.getYCoordinate() &
                        coord2.getYCoordinate() <= coord1.getYCoordinate() + height1)){             // gun in explosion radius
            if ((coord1.getXCoordinate() <= coord2.getXCoordinate() + width2 &
                    coord2.getXCoordinate() + width2 <= coord1.getXCoordinate() + width1) |
                    (coord1.getXCoordinate() <= coord2.getXCoordinate() &
                            coord2.getXCoordinate() <= coord1.getXCoordinate() + width1)){
                return true;
            }

        }
        return false;
    }


    public void moveGun(Coordinate coord, int angle, Projectile ammo){

        setAmmo(ammo);
        setGunPosition(coord);
        setGunAngle(angle);
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

    public boolean isLinear() {
        return isLinear;
    }

    public void setLinear(boolean linear) {
        isLinear = linear;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public void setPowerUpList(ArrayList<PowerUp> powerUpList) {
        this.powerUpList = powerUpList;
    }

    public void setReactionBlockerList(ArrayList<ReactionBlocker> reactionBlockerList) {
        this.reactionBlockerList = reactionBlockerList;
    }

    public void setMoleculeList(ArrayList<Molecule> moleculeList) {
        this.moleculeList = moleculeList;
    }

    public void setL(double L_ratio){
        L = (int) (L_ratio * gameWindowHeight);
    }
}
