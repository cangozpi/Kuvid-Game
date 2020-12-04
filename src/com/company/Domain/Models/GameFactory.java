package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Projectile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameFactory {

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



    }

    public void insertProjectile(Projectile projectile){
            projectileList.add(projectile);
    }
    public void updatePositions(){


    }







}
