package com.company.UI.Objects;

import com.company.Domain.Controller.BuilderHandler;
import com.company.Domain.Models.Builder;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;

public class BuildWindowFactory extends JFrame {
    //singleton
    private static BuildWindowFactory factoryInstance;

    //window variables
    private Builder builder = new Builder();
    public static int windowWidth = 1080;
    public static int windowHeight = 480;

    //attained variables from user
    int difficultyLevel;
    HashMap<AtomType, Integer> atomAmount = new HashMap<>();
    HashMap<PowerUpType, Integer> powerUpAmount = new HashMap<>();
    HashMap<ReactionBlockerType, Integer> reactionBlockerAmount = new HashMap<>();
    HashMap<MoleculeType, Integer> moleculeAmount = new HashMap<>();
    boolean isLinear;

    private BuildWindowFactory(){

    }

    public static BuildWindowFactory getInstance(){
        factoryInstance = factoryInstance==null ? new BuildWindowFactory() : factoryInstance;
        return factoryInstance;
    }

    //render JFrame method
    public void render(){
        //setup main JFrame
        factoryInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        factoryInstance.setResizable(false);
        factoryInstance.setSize(windowWidth, windowHeight);
        factoryInstance.setTitle("Karel Kuvid Builder");
        factoryInstance.setLayout(null);
        factoryInstance.setBackground(Color.DARK_GRAY);

        //panel to add input to
        JPanel inputPanel = new JPanel();
        int rows = 5;
        int columns = 2;

        inputPanel.setLayout(new GridLayout(rows,columns));
        inputPanel.setSize(windowWidth - 50, windowHeight - 50);

        JPanel[][] panelHolder = new JPanel[rows][columns];

        for(int m = 0; m < rows; m++) {//advanced for loop
            for(int n = 0; n < columns; n++) {
                panelHolder[m][n] = new JPanel();
                //panelHolder[m][n].setSize(50, 50);
                inputPanel.add(panelHolder[m][n]);
            }
        }

        //add Input section
        JLabel atomAmountLabel = new JLabel("Atom Amount");
        JLabel moleculeAmountLabel = new JLabel("Molecule Amount");
        JLabel blockerAmountLabel = new JLabel("Reaction Blocker Amount");
        JLabel powerUpAmountLabel = new JLabel("Powerup Amount");

        JLabel difficultyLabel = new JLabel("Select Difficulty");
        JLabel variableAmounts = new JLabel("Select Variable Amounts");
        JLabel moleculeStructureLabel = new JLabel("Select Molecule Structure");


        JSlider difficultySlider = new JSlider(1,3);

        //add to JPanel
        panelHolder[0][0].add(variableAmounts);
        panelHolder[1][0].add(atomAmountLabel);
        panelHolder[2][0].add(moleculeAmountLabel);
        panelHolder[3][0].add(blockerAmountLabel);

        panelHolder[4][0].add(powerUpAmountLabel);
        panelHolder[0][1].add(difficultyLabel);
        panelHolder[1][1].add(difficultySlider);
        panelHolder[2][1].add(moleculeStructureLabel);
        JCheckBox checkBox = new JCheckBox("Linear mode:", true);
        panelHolder[3][1].add(checkBox);

        JTextField[] inputs = new JTextField[4];//array holding input JTextFields
        for(int m = 1; m < rows; m++) {//advanced for loop
            for(int n = 0; n < 1; n++) {
                JTextField textField = new JTextField(7);
                inputs[m-1] = textField;
                panelHolder[m][n].add(textField);
            }
        }

        //Jslider
        JLabel sliderLabel = new JLabel("Difficulty (left: 0 | right:3))");


        inputPanel.add(sliderLabel);
        //inputPanel.add(difficultySlider);

        //submit button
        JButton submitBtn = new JButton("Submit");
        inputPanel.add(submitBtn);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BuilderHandler builderHandler = new BuilderHandler(builder);


                //extract information from JFrame
                difficultyLevel = difficultySlider.getValue();

                for(AtomType element : AtomType.values()){
                    if(isStringInt(inputs[2].getText()))
                        atomAmount.put(element, Integer.parseInt(inputs[2].getText()));
                    else
                        atomAmount.put(element, 100);
                }
                for(PowerUpType element : PowerUpType.values()){
                    if(isStringInt(inputs[2].getText()))
                        powerUpAmount.put(element, Integer.parseInt(inputs[2].getText()));
                    else
                        powerUpAmount.put(element, 20);
                }
                for(ReactionBlockerType element : ReactionBlockerType.values()){
                    if(isStringInt(inputs[2].getText()))
                        reactionBlockerAmount.put(element, Integer.parseInt(inputs[2].getText()));
                    else
                        reactionBlockerAmount.put(element, 10);
                }
                for(MoleculeType element : MoleculeType.values()){
                    if(isStringInt(inputs[3].getText()))
                        moleculeAmount.put(element, Integer.parseInt(inputs[2].getText()));
                    else
                        moleculeAmount.put(element, 100);
                }
                isLinear = checkBox.isSelected();



                //pass extracted variables and call controller class
                builderHandler.buildGame(windowWidth, windowHeight, difficultyLevel, atomAmount, moleculeAmount, powerUpAmount, reactionBlockerAmount);
                BuildWindowFactory.getInstance().getDefaultCloseOperation();
                BuildWindowFactory.getInstance().setVisible(false);
                BuildWindowFactory.getInstance().dispose();

                //Game window below -->
                final GameWindowFactory gamePanel = GameWindowFactory.getInstance();

                //TODO: remove code below only *******************************
                //add elements t
                // o be drawn to the list
                // Note: It is important to add BackgroundObject Last to draw it at the bottom layer of JFrame

                GameObject lol = new GunObject(new Coordinate(((GameWindowFactory.getInstance().windowWidth/2) - GameWindowFactory.getInstance().L / 4), GameWindowFactory.getInstance().windowHeight - (1.75) * GameWindowFactory.getInstance().L), (int)(GameWindowFactory.L / 2), GameWindowFactory.L, "shooter.png", 0);
                gamePanel.addToObjectList(lol);
                GameObject loo = new BackgroundObject(new Coordinate(0, -10), GameWindowFactory.getInstance().windowWidth, GameWindowFactory.getInstance().windowHeight, "kuvid_bc.png");
                gamePanel.addToObjectList(loo);
                //***********************************

                //render Game Frame
                gamePanel.render();
            }



        });
        //add panel to the frame
        factoryInstance.getContentPane().add(inputPanel);
        factoryInstance.setVisible(true);
    }
    private boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


}
