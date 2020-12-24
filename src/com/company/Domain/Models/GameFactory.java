package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Observer.GunObserver;
import com.company.Domain.Observer.IGunListener;
import com.company.Domain.Utility.Coordinate;
import com.company.UI.Objects.GameWindowFactory;
import com.company.UI.Observer.GameObserver;

import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameFactory extends GameObserver implements IGunListener {
    private boolean isRunning;

    private static GameFactory instance;
    private int gameWindowHeight;
    private int gameWindowWidth;
    private int difficulty;
    public int L;
    public int moleculeSender;

    private Coordinate gunPosition;
    private int gunAngle;
    private ArrayList<Atom> atomList = new ArrayList<>();
    private ArrayList<PowerUp> shotPowerUpList = new ArrayList<>();
    private ArrayList<PowerUp> powerUpList = new ArrayList<>();
    private ArrayList<ReactionBlocker> reactionBlockerList = new ArrayList<>();
    private ArrayList<Molecule> moleculeList = new ArrayList<>();
    private Atom ammoAtom;
    private PowerUp ammoPowerUp;
    private GameWindowFactory factory;
    private int fallSpeed;

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
        GunObserver gunObserver = GunFactory.getInstance();
        gunObserver.addListener(this);
        setGunPosition(new Coordinate(((gameWindowWidth/2) - L / 4),
                gameWindowHeight -  L));
        setGunAngle(90);
        gameLoop();
    }

    //gameLoop() handles both game clock and alien clock
    public void gameLoop() {
        gameClock.start();
        goodalienClock.start();
        badalienClock.start();
    }

    Timer badalienClock =  new Timer(5000, new ActionListener() { // checks for cat icons collusion

        @Override
        public void actionPerformed(ActionEvent e) {
            BadAlienFactory badAlienFactory = BadAlienFactory.getInstance();
            ReactionBlocker reactionBlocker = badAlienFactory.sendReactionBlocker();
            if (reactionBlocker!=null)
                insertReactionBlocker(reactionBlocker);
        }
    });

    Timer goodalienClock =  new Timer(5000, new ActionListener() { // checks for cat icons collusion
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
    Timer gameClock =  new Timer(16, new ActionListener() { // checks for cat icons collusion

        @Override
        public void actionPerformed(ActionEvent e) {
            updatePositions();
        }
    });



    public void pauseGame(){
        gameClock.stop();
    }

    public void resumeGame(){
        gameClock.start();
    }

    public void insertAtom(Atom atom){    //projectiles shot by the gun


        atomList.add(atom);

    }
    public void insertPowerUpShotFromGun(PowerUp powerUp){    //projectiles shot by the gun


        shotPowerUpList.add(powerUp);

    }
    public void insertProjectile(Projectile projectileFromGun){    //projectiles shot by the gun

      //  projectileFromGun.


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
        if (!atomList.isEmpty()){
            for (Atom atom : atomList) {
                atom.move();
            }
        }
        if (!shotPowerUpList.isEmpty()){
            for (PowerUp powerUp : shotPowerUpList) {
                powerUp.move();
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




        GameFactory.super.positionUpdateEvent(moleculeList, atomList, shotPowerUpList, reactionBlockerList,powerUpList, gunPosition, gunAngle);
    }

    public void checkCollisions(){
        // iterates over reaction blocker list
        //

    }

    @Override
    public void gunMoved(Coordinate coord, int angle, Atom atom, PowerUp powerUp){
        if(atom == null){
            setAmmoPowerUp(powerUp);
        }else{
            setAmmoAtom(atom);
        }
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

    public ArrayList<Atom> getAtomList() {
        return atomList;
    }

    public void setAtomList(ArrayList<Atom> atomList) {
        this.atomList = atomList;
    }

    public ArrayList<PowerUp> getShotPowerUpList() { return shotPowerUpList; }

    public void setShotPowerUpList(ArrayList<PowerUp> shotPowerUpList) { this.shotPowerUpList = shotPowerUpList; }

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

    public Atom getAmmoAtom() {
        return ammoAtom;
    }

    public void setAmmoAtom(Atom ammoAtom) {
        this.ammoAtom = ammoAtom;
    }

    public PowerUp getAmmoPowerUp() {
        return ammoPowerUp;
    }

    public void setAmmoPowerUp(PowerUp ammoPowerUp) {
        this.ammoPowerUp = ammoPowerUp;
    }

    public static void setInstance(GameFactory instance) { GameFactory.instance = instance; }

}
