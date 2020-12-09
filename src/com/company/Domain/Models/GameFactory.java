package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Models.Projectile.ReactionBlocker;
import com.company.UI.Objects.BuildWindowFactory;
import com.company.UI.Objects.GameWindowFactory;
import com.company.UI.Objects.Observer.GameObserver;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameFactory extends GameObserver {
    private boolean isRunning;

    private static GameFactory instance;
    private int gameWindowHeight;
    private int gameWindowWidth;
    private int difficulty;
    public int L;
    private List<Projectile> objectList;
    private List<Projectile> projectileList;
    private List<PowerUp> powerUpList;
    private List<ReactionBlocker> reactionBlockerList;
    private List<Molecule> moleculeList;
    private Projectile ammo;
    private GameWindowFactory factory;


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
    public void gameLoop() {

        //16.68ms for 60FPS
        Timer gameClock =  new Timer(3000, new ActionListener() { // checks for cat icons collusion

            @Override
            public void actionPerformed(ActionEvent e) {
                factory.render();//?
                updatePositions();

            }
        });

        gameClock.start();
    }

    public void insertProjectile(Projectile projectile){

        objectList.add(projectile);
        projectileList.add(projectile);

    }

    public void insertMolecule(Molecule molecule){
        objectList.add(molecule);
        moleculeList.add(molecule);
    }

    public void insertPowerUp(PowerUp powerUp){
        objectList.add(powerUp);
        projectileList.add(powerUp);
    }

    public void insertReactionBlocker(ReactionBlocker reactionBlocker){
        objectList.add(reactionBlocker);
        projectileList.add(reactionBlocker);
    }

    public void updatePositions(){
        for (Projectile projectile : objectList) {
            projectile.move();
        }

        for (Projectile projectile : projectileList) {
            projectile.move();
        }

        for (ReactionBlocker reactionBlocker : reactionBlockerList) {
            reactionBlocker.move();
        }

        for (PowerUp powerUp : powerUpList) {
            powerUp.move();
        }
        checkCollisions();
        GameFactory.super.positionUpdateEvent(objectList);
    }

    public void checkCollisions(){
        // iterates over reaction blocker list
        //

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

    public List<Projectile> getProjectileList() {
        return projectileList;
    }

    public void setProjectileList(List<Projectile> projectileList) {
        this.projectileList = projectileList;
    }

    public void setGameWindowHeight(int gameWindowHeight) {
        this.gameWindowHeight = gameWindowHeight;
    }

    public void setGameWindowWidth(int gameWindowWidth) {
        this.gameWindowWidth = gameWindowWidth;
    }

    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public int getL() { return L; }

    public List<PowerUp> getPowerUpList() { return powerUpList; }

    public List<ReactionBlocker> getReactionBlockerList() { return reactionBlockerList; }

    public List<Molecule> getMoleculeList() { return moleculeList; }

    public Projectile getAmmo() { return ammo; }

    public void setAmmo(Projectile ammo){ this.ammo = ammo;}

    public static void setInstance(GameFactory instance) { GameFactory.instance = instance; }

}
