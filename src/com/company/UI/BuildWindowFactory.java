package com.company.UI;

import com.company.Domain.Controller.BuilderHandler;
import com.company.Domain.Models.Builder;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.AtomType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.UI.Objects.BackgroundObject;
import com.company.UI.Objects.GameObject;
import com.company.UI.Objects.GameWindowFactory;
import com.company.UI.Objects.GunObject;
import com.company.Utils.CenterWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BuildWindowFactory extends JFrame {
    //singleton
    private static BuildWindowFactory factoryInstance;

    //window variables
    private Builder builder = new Builder();
    public static int windowWidth = 720;
    public static int windowHeight = 360;

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
        factoryInstance.setTitle("KUVid Builder");
        factoryInstance.setLayout(new FlowLayout());
        factoryInstance.setBackground(Color.DARK_GRAY);
        CenterWindow.centerWindow(this);
        //panel to add input to
        JPanel inputPanel = new JPanel();
        int rows = 5;
        int columns = 2;

        inputPanel.setLayout(new GridLayout(rows,columns));

        JPanel[][] panelHolder = new JPanel[rows][columns];

        for(int m = 0; m < rows; m++) {
            for(int n = 0; n < columns; n++) {
                panelHolder[m][n] = new JPanel();
                inputPanel.add(panelHolder[m][n]);
            }
        }

        //add Input section
        JLabel atomAmountLabel = new JLabel("Atom Amount:");
        JLabel moleculeAmountLabel = new JLabel("Molecule Amount:");
        JLabel blockerAmountLabel = new JLabel("Reaction Blocker Amount:");
        JLabel powerUpAmountLabel = new JLabel("Powerup Amount:");

        JLabel difficultyLabel = new JLabel("Select Difficulty");
        JLabel variableAmounts = new JLabel("Select Variable Amounts");
        JLabel moleculeStructureLabel = new JLabel("Select Molecule Structure");

        JLabel easySliderLabel = new JLabel("Easy");
        JLabel hardSliderLabel = new JLabel("Hard");


        JSlider difficultySlider = new JSlider(1,3);

        //add to JPanel
        panelHolder[0][0].add(variableAmounts);
        panelHolder[1][0].add(atomAmountLabel);
        panelHolder[2][0].add(moleculeAmountLabel);
        panelHolder[3][0].add(blockerAmountLabel);
        panelHolder[4][0].add(powerUpAmountLabel);

        panelHolder[0][1].add(difficultyLabel);
        panelHolder[1][1].add(easySliderLabel);
        panelHolder[1][1].add(difficultySlider);
        panelHolder[1][1].add(hardSliderLabel);
        panelHolder[2][1].add(moleculeStructureLabel);
        JCheckBox checkBox = new JCheckBox("Linear mode", true);
        panelHolder[3][1].add(checkBox);

        JTextField[] inputs = new JTextField[4];//array holding input JTextFields
        for(int m = 1; m < rows; m++) {//advanced for loop
            for(int n = 0; n < 1; n++) {
                JTextField textField = new JTextField(7);
                inputs[m-1] = textField;
                panelHolder[m][n].add(textField);
            }
        }

        //submit button
        JButton submitBtn = new JButton("Play!");
        panelHolder[4][1].add(submitBtn);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BuilderHandler builderHandler = new BuilderHandler(builder);


                //extract information from JFrame
                difficultyLevel = difficultySlider.getValue();
                isLinear = checkBox.isSelected();
                for(AtomType element : AtomType.values()){
                    if(isStringInt(inputs[0].getText()))
                        atomAmount.put(element, Integer.parseInt(inputs[0].getText()));
                    else
                        atomAmount.put(element, 100);
                }
                for(PowerUpType element : PowerUpType.values()){
                    if(isStringInt(inputs[3].getText()))
                        powerUpAmount.put(element, Integer.parseInt(inputs[3].getText()));
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
                    if(isLinear){
                        if(!element.equals(MoleculeType.ALPHA_2) && !element.equals(MoleculeType.BETA_2)) {
                            if (isStringInt(inputs[1].getText()))
                                moleculeAmount.put(element, Integer.parseInt(inputs[1].getText()));
                            else
                                moleculeAmount.put(element, 100);
                        }
                    }else{
                        if(!element.equals(MoleculeType.ALPHA_1) && !element.equals(MoleculeType.BETA_1)) {
                            if (isStringInt(inputs[1].getText()))
                                moleculeAmount.put(element, Integer.parseInt(inputs[1].getText()));
                            else
                                moleculeAmount.put(element, 100);
                        }
                    }
                }



                //pass extracted variables and call controller class
                builderHandler.buildGame(windowWidth, windowHeight, difficultyLevel, atomAmount, moleculeAmount, powerUpAmount, reactionBlockerAmount, isLinear);
                BuildWindowFactory.getInstance().getDefaultCloseOperation();
                BuildWindowFactory.getInstance().setVisible(false);
                BuildWindowFactory.getInstance().dispose();

                //Game window below -->
                final GameWindowFactory gamePanel = GameWindowFactory.getInstance();

                //TODO: remove code below only *******************************
                //add elements t
                // o be drawn to the list
                // Note: It is important to add BackgroundObject Last to draw it at the bottom layer of JFrame

                GameObject lol = new GunObject(new Coordinate(((GameWindowFactory.getInstance().windowWidth/2) - GameWindowFactory.getInstance().L / 4), GameWindowFactory.getInstance().windowHeight - (1.75) * GameWindowFactory.getInstance().L),0);
                gamePanel.addToObjectList(lol);
                GameObject bgObject = new BackgroundObject(new Coordinate(0, -10));
                gamePanel.addToObjectList(bgObject);
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
