package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Projectile;

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
    private List<Projectile> projectileList;


    private GameFactory(){

    }

    public static GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();
        }
        return instance;
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

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;

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
            projectileList.add(projectile);
    }
    public void updatePositions(){


    }







}
