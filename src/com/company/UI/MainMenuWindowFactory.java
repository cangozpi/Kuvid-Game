
package com.company.UI;

import com.company.Domain.Controller.BlenderHandler;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.Inventory;
import com.company.Enums.AtomType;
import com.company.UI.Objects.GameObject;
import com.company.UI.Objects.GameWindowFactory;
import com.company.Utils.CenterWindow;
import com.company.repository.DatabaseAdapter;
import com.company.repository.LocalDB;
import com.company.repository.MongoDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainMenuWindowFactory extends JFrame {
    private static MainMenuWindowFactory factoryInstance;
    public static int windowWidth = 720;
    public static int windowHeight = 480;
    private DatabaseAdapter databaseAdapter;
    private Inventory inventory;
    private JPanel[][] panelHolder;
    private MainMenuWindowFactory(){

    }

    public static MainMenuWindowFactory getInstance(){
        factoryInstance = factoryInstance==null ? new MainMenuWindowFactory() : factoryInstance;
        return factoryInstance;
    }

    public void render() {
        this.setResizable(false);
        this.setSize(windowWidth, windowHeight);
        this.setTitle("KUVid Menu");
        this.setLayout(new FlowLayout());
        this.setBackground(Color.DARK_GRAY);

        CenterWindow.centerWindow(this);
        JPanel inputPanel = new JPanel();
        int rows = 3;
        int columns = 7;

        inputPanel.setLayout(new GridLayout(rows, columns));

        panelHolder = new JPanel[rows][columns];

        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < columns; n++) {
                panelHolder[m][n] = new JPanel();
                inputPanel.add(panelHolder[m][n]);
            }
        }
       // JButton loadButton = new JButton("LOAD GAME");
        //  panelHolder[0][3].add(loadButton);
        // loadButton.addActionListener(new ActionListener() {
        //    @Override
        //   public void actionPerformed(ActionEvent actionEvent) {
        //      MainMenuWindowFactory.getInstance().dispose();
        //     databaseAdapter.loadGame();
        //       GameFactory.getInstance().resumeGame();
        //  }

        // });
        JButton newButton = new JButton("NEW GAME");
        panelHolder[2][3].add(newButton);
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenuWindowFactory.getInstance().dispose();
                BuildWindowFactory buildWindow = BuildWindowFactory.getInstance();
                buildWindow.render();


            }

        });
        this.getContentPane().add(inputPanel);
        this.setVisible(true);
    }
}