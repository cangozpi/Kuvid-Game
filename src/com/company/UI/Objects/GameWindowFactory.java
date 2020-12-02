package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindowFactory extends JFrame {
/*
    Main UI Frame, uses Singleton Pattern
 */

    public static int windowWidth = 852;
    public static int windowHeight = 480;
    public static int L = windowHeight / 10;

    //list holding JPanel's to be drawn
    private ArrayList<GameObject> objectList = new ArrayList<>();

    private static GameWindowFactory factoryInstance; //holds single instance variable of this class

    private GameWindowFactory()  {

    }

    public static GameWindowFactory getInstance(){
        if(factoryInstance == null){
            factoryInstance = new GameWindowFactory();
        }
        return factoryInstance;
    }

    public void render(){
        //setup main JFrame
        factoryInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        factoryInstance.setResizable(false);
        factoryInstance.setSize(windowWidth, windowHeight);
        factoryInstance.setTitle("Karel Kuvid");
        factoryInstance.setLayout(null);

        //Dark mode for aesthetic purposes
        factoryInstance.getContentPane().setBackground(Color.DARK_GRAY);

        //draw JPanel elements from list
        for(GameObject element: objectList){
            element.draw();
        }

        factoryInstance.setVisible(true);

    }

    public void addToObjectList(GameObject object){
        objectList.add(object);
    }
}
