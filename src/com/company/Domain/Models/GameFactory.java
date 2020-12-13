package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Observer.IGunListener;
import com.company.Domain.Utility.Coordinate;
import com.company.UI.Objects.GameWindowFactory;
import com.company.UI.Objects.Observer.GameObserver;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private List<Atom> atomList;
    private List<PowerUp> shotPowerUpList;
    private List<PowerUp> powerUpList;
    private List<ReactionBlocker> reactionBlockerList;
    private List<Molecule> moleculeList;
    private Atom ammoAtom;
    private PowerUp ammoPowerUp;
    private GameWindowFactory factory;
    private int fallSpeed;
    private Timer gameClock;

    private GameFactory(){
        super(); //necessary for initializing Observer
        factory = GameWindowFactory.getInstance();
    }

    public static GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();
        }
        return instance;
    }




    public void startGame(){
        L = gameWindowHeight / 10;

        gameLoop();
    }

    //gameLoop() handles both game clock and alien clock
    public void gameLoop() {

        boolean flag = false;

        //16.68ms for 60FPS
            gameClock =  new Timer(300, new ActionListener() { // checks for cat icons collusion

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
                alienFactoryInstance.sendMolecule();
            }
        });

       // alienClock.start();
    }

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
        if (moleculeList != null){
            for (Projectile projectile : moleculeList) {
                projectile.move();
            }
        }
        if (atomList != null){
            for (Projectile projectile : atomList) {
                projectile.move();
            }
        }
        if (shotPowerUpList != null){
            for (PowerUp powerUp : shotPowerUpList) {
                powerUp.move();
            }
        }

        if ( reactionBlockerList != null) {
            for (ReactionBlocker reactionBlocker : reactionBlockerList) {
                reactionBlocker.move();
            }
        }
        if (powerUpList != null){
            for (PowerUp powerUp : powerUpList) {
                powerUp.move();
            }
        }   

        checkCollisions();




        GameFactory.super.positionUpdateEvent(moleculeList, atomList, shotPowerUpList, reactionBlockerList,powerUpList);
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

    public int getGameWindowHeight() {
        return gameWindowHeight;
    }

    public int getGameWindowWidth() {
        return gameWindowWidth;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public List<Atom> getAtomList() {
        return atomList;
    }

    public void setAtomList(List<Atom> atomList) {
        this.atomList = atomList;
    }

    public List<PowerUp> getShotPowerUpList() { return shotPowerUpList; }

    public void setShotPowerUpList(List<PowerUp> shotPowerUpList) { this.shotPowerUpList = shotPowerUpList; }

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

    public List<PowerUp> getPowerUpList() { return powerUpList; }

    public List<ReactionBlocker> getReactionBlockerList() { return reactionBlockerList; }

    public List<Molecule> getMoleculeList() { return moleculeList; }

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
