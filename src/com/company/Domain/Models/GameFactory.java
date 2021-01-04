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
        ArrayList<Projectile> projectileRemovalList = new ArrayList<>();
        ArrayList<Molecule> moleculeRemovalList = new ArrayList<>();
        ArrayList<PowerUp> powerUpRemovalList = new ArrayList<>();

        ArrayList<ReactionBlocker> reactionBlockerRemovalList = new ArrayList<>();
        ArrayList<ReactionBlocker> explosionList = new ArrayList<>();

        //molecules
        for (Molecule molecule : moleculeList) {
            if (molecule.getYCoordinate() + molecule.getHeight() > gameWindowHeight - 35  ){         // bottom edge check
                moleculeRemovalList.add(molecule);
            }
            for(ReactionBlocker reactionBlocker : reactionBlockerList){
                if(hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), L/2, L/2, molecule.getCoordinate(), molecule.getWidth(), molecule.getHeight() )){
                    explosionList.add(reactionBlocker);
                    moleculeRemovalList.add(molecule);
                }

            }
        }

        for (Molecule element : moleculeRemovalList){
            moleculeList.remove(element);
        }
        moleculeRemovalList.clear();


        //powerups
        for (PowerUp powerUp : powerUpList) {
            if (powerUp.getYCoordinate() + powerUp.getHeight() > gameWindowHeight - 35 ){         // bottom edge check
                powerUpRemovalList.add(powerUp);
            }

            if ( gunPosition.getYCoordinate() <= powerUp.getYCoordinate() + powerUp.getHeight()){             // gun collects powerUp
                if ((powerUp.getXCoordinate() <= gunPosition.getXCoordinate() + L/2 &
                        gunPosition.getXCoordinate() + L/2 <= powerUp.getXCoordinate() + powerUp.getWidth()) |
                        (powerUp.getXCoordinate() <= gunPosition.getXCoordinate() &
                                gunPosition.getXCoordinate() <= powerUp.getXCoordinate() + powerUp.getWidth())){
                    powerUpRemovalList.add(powerUp);
                    Inventory.getInstance().addPowerUp(powerUp.getProjectileType());
                }
            }

            for(ReactionBlocker reactionBlocker : reactionBlockerList){
                if(hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), L/2, L/2, powerUp.getCoordinate(), powerUp.getWidth(), powerUp.getHeight() )){
                    explosionList.add(reactionBlocker);
                    powerUpRemovalList.add(powerUp);
                }

            }
        }

        for (PowerUp element : powerUpRemovalList){
            powerUpList.remove(element);
        }
       powerUpRemovalList.clear();

        // projectiles
        for (Projectile projectile : projectileFromGunList) {
            if (projectile.getYCoordinate() - projectile.getHeight() <= 0 ){         // top edge check
                projectileRemovalList.add(projectile);
            }
            for(ReactionBlocker reactionBlocker : reactionBlockerList){
                if(hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), L/2, L/2, projectile.getCoordinate(), projectile.getWidth(), projectile.getHeight() )){
                    if(projectile instanceof Atom){
                        explosionList.add(reactionBlocker);
                        projectileRemovalList.add(projectile);
                    } else {                                                      //TODO powerup shot from gun interacts differently with reaction blocker
                        reactionBlockerRemovalList.add(reactionBlocker);
                        projectileRemovalList.add(projectile);
                    }

                }

            }
        }


        for (Projectile element : projectileRemovalList){
            projectileFromGunList.remove(element);
        }
        projectileRemovalList.clear();



        // reaction blockers
        for (ReactionBlocker reactionBlocker : reactionBlockerList ) {
            if(hasCollidedReactionBlocker(reactionBlocker.getCoordinate(),L/2,L/2,getGunPosition(), L/2,L)){   //gun check
                explosionList.add(reactionBlocker);
            }else if (reactionBlocker.getYCoordinate() + 0.5 * L >= gameWindowHeight - 35 ){         // bottom edge check
                explosionList.add(reactionBlocker);
            }

        }
//        //Ammo Deletion (if required)
//        Gun.setAmmo(null);
//        AtomSelector.selectAtom();

        for (ReactionBlocker reactionBlocker : explosionList){          // TODO explosion
            reactionBlockerList.remove(reactionBlocker);

            if (hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), 2*L, 2*L, getGunPosition(), L/2, L)){   // collision explosion radius
                //TODO lose health
            }
            for(PowerUp powerUp : powerUpList){
                if (hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), 2*L, 2*L, powerUp.getCoordinate(), powerUp.getWidth(), powerUp.getHeight())){
                    powerUpRemovalList.add(powerUp);
                }
            }
            for(Molecule molecule : moleculeList){
                if (hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), 2*L, 2*L, molecule.getCoordinate(), molecule.getWidth(), molecule.getHeight())){
                    moleculeRemovalList.add(molecule);
                }
            }
            for(Projectile projectile : projectileFromGunList){
                if (hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), 2*L, 2*L, projectile.getCoordinate(), projectile.getWidth(), projectile.getHeight())){
                    projectileRemovalList.add(projectile);
                }
            }

            for(ReactionBlocker blocker : reactionBlockerList){
                if (hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), 2*L, 2*L, blocker.getCoordinate(), L/2, L/2)){
                    reactionBlockerRemovalList.add(blocker);
                }
            }

        }
        explosionList.clear();

        for (Projectile element : projectileRemovalList){
            projectileFromGunList.remove(element);
        }
        projectileRemovalList.clear();

        for (Molecule element : moleculeRemovalList){
            moleculeList.remove(element);
        }
        moleculeRemovalList.clear();

        for (ReactionBlocker element :reactionBlockerRemovalList){
            reactionBlockerList.remove(element);
        }
        reactionBlockerRemovalList.clear();

        for (PowerUp element : powerUpRemovalList){
            powerUpList.remove(element);
        }
        powerUpRemovalList.clear();

        for (Projectile projectile : projectileFromGunList) {
            if (projectile.getYCoordinate() - projectile.getHeight() <= 0 ){         // top edge check
                projectileRemovalList.add(projectile);
            }
            if(projectile instanceof Atom){
                for(Molecule molecule : moleculeList){               //TODO atom collects molecule not powerup
                  if(hasCollided(molecule,projectile)){
                      moleculeRemovalList.add(molecule);
                      projectileRemovalList.add(projectile);
                   }
                }
            }
        }
        for (Projectile element : projectileRemovalList){
            projectileFromGunList.remove(element);
        }
        projectileRemovalList.clear();

        for (Molecule element : moleculeRemovalList){
            moleculeList.remove(element);
        }
        moleculeRemovalList.clear();

        for (ReactionBlocker element :reactionBlockerRemovalList){
            reactionBlockerList.remove(element);
        }
       reactionBlockerRemovalList.clear();









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
        return time *33 / 100;
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

    public void resetObjectLists(){
        projectileFromGunList = new ArrayList<>();
        powerUpList = new ArrayList<>();
        reactionBlockerList = new ArrayList<>();
        moleculeList = new ArrayList<>();

        gunPosition = new Coordinate(((GameFactory.getInstance().getGameWindowWidth()/2) - GameFactory.getInstance().getL() / 4),
                GameFactory.getInstance().getGameWindowHeight() +  GameFactory.getInstance().getL());
        gunAngle = 90;
    }
}
