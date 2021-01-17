package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Models.Projectile.Decorator.AtomDecorator;
import com.company.Domain.Utility.Coordinate;

import com.company.Domain.Utility.Velocity;
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

    private int health;
    private boolean isLinear;
    private double score;
    private int time;

    private GameFactory(){
        super(); //necessary for initializing Observer
        time = 0;
        score = 0;
        health = 100;

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

    Timer badAlienClock =  new Timer(7000, new ActionListener() {

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
            if(health == 0){
            pauseGame();
            }
            if(getTime()>300){
            pauseGame();
            }
            if(Inventory.getInstance().outOfAtoms()){
            pauseGame();
            }
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
            for(ReactionBlocker reactionBlocker : reactionBlockerList){                              // reaction blocker check

                if(isSameType(molecule,reactionBlocker)){
                    if(hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), L/2, molecule.getCoordinate(), molecule.getWidth(), molecule.getHeight())){
                         reactionBlockerRemovalList.add(reactionBlocker);
                         moleculeRemovalList.add(molecule);
                    }
                }
            }
        }

        for (Molecule element : moleculeRemovalList){
            moleculeList.remove(element);
        }
        moleculeRemovalList.clear();
        for (ReactionBlocker element :reactionBlockerRemovalList){
            reactionBlockerList.remove(element);
        }
        reactionBlockerRemovalList.clear();


        //powerups
        for (PowerUp powerUp : powerUpList) {
            if (powerUp.getYCoordinate() + powerUp.getHeight() > gameWindowHeight - 35 ){         // bottom edge check
                powerUpRemovalList.add(powerUp);
            }
            if ( (powerUp.getYCoordinate() <= gunPosition.getYCoordinate() + L &
                    gunPosition.getYCoordinate() + L <= powerUp.getYCoordinate() + powerUp.getHeight()) |                      // gun collects powerUp
                    (powerUp.getYCoordinate() <= gunPosition.getYCoordinate() &
                            gunPosition.getYCoordinate() <= powerUp.getYCoordinate() + powerUp.getHeight())){
                if ((powerUp.getXCoordinate() <= gunPosition.getXCoordinate() +L/2 &
                        gunPosition.getXCoordinate() + L/2 <= powerUp.getXCoordinate() + powerUp.getWidth()) |
                        (powerUp.getXCoordinate() <= gunPosition.getXCoordinate() &
                                gunPosition.getXCoordinate() <= powerUp.getXCoordinate() + powerUp.getWidth())){
                        powerUpRemovalList.add(powerUp);
                        Inventory.getInstance().addPowerUp(powerUp.getProjectileType());
                }
            }

        }

        for (PowerUp element : powerUpRemovalList){
            powerUpList.remove(element);
        }
        powerUpRemovalList.clear();


        // projectiles from gun
        for (Projectile projectile : projectileFromGunList) {
            if (projectile.getYCoordinate() - projectile.getHeight() <= 0 ){         // top edge check
                projectileRemovalList.add(projectile);
            }else if ( projectile.getXCoordinate() >= gameWindowWidth){
                projectile.setVelocity(new Velocity(projectile.getVelocity().getAngle() + 90,projectile.getVelocity().getSpeed()));
            }else if (projectile.getXCoordinate() <= 0){
                projectile.setVelocity(new Velocity(projectile.getVelocity().getAngle() - 90,projectile.getVelocity().getSpeed()));
            }

            for(ReactionBlocker reactionBlocker : reactionBlockerList){
                if(isSameType(projectile,reactionBlocker)){
                    if(hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), L/2, projectile.getCoordinate(), projectile.getWidth(), projectile.getHeight() )){
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

        for (ReactionBlocker element :reactionBlockerRemovalList){
            reactionBlockerList.remove(element);
        }
        reactionBlockerRemovalList.clear();


        // reaction blockers
        for (ReactionBlocker reactionBlocker : reactionBlockerList ) {
            if(hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), L/2, getGunPosition(), L/2,L)){   //gun check
                explosionList.add(reactionBlocker);
            }else if (reactionBlocker.getYCoordinate() + L/4 >= gameWindowHeight - 35 ){         // bottom edge check
                explosionList.add(reactionBlocker);
            }
        }

        //Ammo Deletion (if required)
        // Gun.setAmmo(null);
        //AtomSelector.selectAtom();

        for (ReactionBlocker reactionBlocker : explosionList){
            reactionBlockerList.remove(reactionBlocker);
            int distanceToGun = distanceBetween(gunPosition,reactionBlocker.getCoordinate());
            if (distanceToGun < 2*L){   // collision explosion radius
                health -= gameWindowHeight/distanceToGun;    //lose health
            }

            for(Molecule molecule : moleculeList){
                if (hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), 2*L, molecule.getCoordinate(), molecule.getWidth(), molecule.getHeight())){
                    moleculeRemovalList.add(molecule);
                }
            }
            for(Projectile projectile : projectileFromGunList){
                if(projectile instanceof Atom){
                    if (hasCollidedReactionBlocker(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), 2*L, projectile.getCoordinate(), projectile.getWidth(), projectile.getHeight())){
                        projectileRemovalList.add(projectile);
                    }
                }
            }
        }
        explosionList.clear();
        /*
        for (Projectile element : projectileRemovalList){
            projectileFromGunList.remove(element);
        }
        projectileRemovalList.clear();

        for (Molecule element : moleculeRemovalList){
            moleculeList.remove(element);
        }
        moleculeRemovalList.clear();
        */

        for (Projectile projectile : projectileFromGunList) {
            if(projectile.getProjectileType().toString().contains("atom")){
                for(Molecule molecule : moleculeList){
                    if(isSameType(projectile,molecule)){                     //collect molecule gain score
                        if(hasCollided(molecule,projectile)){
                            moleculeRemovalList.add(molecule);
                            projectileRemovalList.add(projectile);
                            AtomDecorator atom = (AtomDecorator)projectile;
                            this.score += atom.getEfficiency();
                   }
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

        }










    public boolean hasCollidedReactionBlocker(Coordinate reactionBlockerPosition,  int blockerWidth,  int blockerHeight, int collisionRadius, Coordinate objectPosition, int objectWidth, int objectHeight){
        double centerX = reactionBlockerPosition.getXCoordinate() + blockerWidth/2.0;
        double centerY = reactionBlockerPosition.getYCoordinate() - blockerHeight/2.0;
        Coordinate center = new Coordinate(centerX,centerY);                               //center of the reaction blocker
        Coordinate topRightCorner = new Coordinate(objectPosition.getXCoordinate()+objectWidth, objectPosition.getY());
        Coordinate bottomRightCorner = new Coordinate(objectPosition.getXCoordinate()+objectWidth, objectPosition.getY() + objectHeight);
        Coordinate bottomLeftCorner = new Coordinate(objectPosition.getXCoordinate(), objectPosition.getY() + objectHeight);
        if(distanceBetween(center,objectPosition)< collisionRadius){                                   // coord2 is the top left coordinate of the object
            return true;
        } else if(distanceBetween(center,topRightCorner)< collisionRadius){
            return true;
        } else if(distanceBetween(center, bottomLeftCorner)< collisionRadius){
            return true;
        } else if(distanceBetween(center,bottomRightCorner)< collisionRadius){
            return true;
        }
        return false;

        /*                                                                            //rectangular collision check
        if ( (coord1.getYCoordinate() <= coord2.getYCoordinate() + height2 &
                coord2.getYCoordinate() + height2 <= coord1.getYCoordinate() + height1) |
                (coord1.getYCoordinate() <= coord2.getYCoordinate() &
                        coord2.getYCoordinate() <= coord1.getYCoordinate() + height1)){
            if ((coord1.getXCoordinate() <= coord2.getXCoordinate() + width2 &
                    coord2.getXCoordinate() + width2 <= coord1.getXCoordinate() + width1) |
                    (coord1.getXCoordinate() <= coord2.getXCoordinate() &
                            coord2.getXCoordinate() <= coord1.getXCoordinate() + width1)){
                return true;
            }

        }*/

    }
    public boolean isSameType(Projectile projectile1, Projectile projectile2){


        if(projectile1.getProjectileType().toString().contains("ALPHA")){
            if(projectile2.getProjectileType().toString().contains("ALPHA")){
                return true;
            }else return false;
        } else if(projectile1.getProjectileType().toString().contains("BETA")){
            if(projectile2.getProjectileType().toString().contains("BETA")){
                return true;
            }else return false;
        } else if(projectile1.getProjectileType().toString().contains("GAMMA")){
            if(projectile2.getProjectileType().toString().contains("GAMMA")){
                return true;
            }else return false;
        } else if(projectile1.getProjectileType().toString().contains("SIGMA")){
            if(projectile2.getProjectileType().toString().contains("SIGMA")){
                return true;
            }else return false;
        }

        return false;
    }

    public int distanceBetween(Coordinate coord1, Coordinate coord2){
        return (int) Math.round(Math.sqrt(
                (coord1.getX() - coord2.getX()) * (coord1.getX() - coord2.getX()) +
                        (coord1.getY() - coord2.getY()) * (coord1.getY() - coord2.getY())
                )
        );
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getTime() {
        return time *33 / 1000;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
