package com.company;

import com.company.Domain.Utility.Coordinate;
import com.company.UI.Objects.*;

public class Main {

    public static void main(String[] args) {


        //Builder Mode initialization
        BuildWindowFactory buildWindow = BuildWindowFactory.getInstance();
        buildWindow.render();



        //Game window below -->
        final GameWindowFactory gamePanel = GameWindowFactory.getInstance();

        //TODO: remove code below only *******************************
        //add elements to be drawn to the list
        // Note: It is important to add BackgroundObject Last to draw it at the bottom layer of JFrame
        GameObject lol = new GunObject(new Coordinate(((GameWindowFactory.getInstance().windowWidth/2) - GameWindowFactory.getInstance().L / 4), GameWindowFactory.getInstance().windowHeight - (1.75) * GameWindowFactory.getInstance().L), (int)(GameWindowFactory.L * 0.5), GameWindowFactory.L, "GunImage.png", 0);
        gamePanel.addToObjectList(lol);
        GameObject loo = new BackgroundObject(new Coordinate(0, -10), GameWindowFactory.getInstance().windowWidth, GameWindowFactory.getInstance().windowHeight, "BackgroundImage.png");
        gamePanel.addToObjectList(loo);
        //***********************************


        //render Game Frame
        gamePanel.render();


    }
}
