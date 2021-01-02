package com.company.UI;

import com.company.Domain.Controller.BuilderHandler;
import com.company.Domain.Models.Builder;
import com.company.Domain.Models.GameFactory;
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
    public static int windowWidth = 1440;
    public static int windowHeight = 980;

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
        int rows = 6;
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

               JLabel LRatioSliderLabel = new JLabel("L Ratio Slider");
        JSlider LRatioSlider = new JSlider(2,8);
        LRatioSlider.setValue(2);

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
        JCheckBox checkBox = new JCheckBox("Nonlinear mode", true);
        panelHolder[3][1].add(checkBox);
        panelHolder[4][1].add(LRatioSliderLabel);
        panelHolder[4][1].add(LRatioSlider);
        JTextField[] inputs = new JTextField[5];//array holding input JTextFields
        for(int m = 1; m < rows; m++) {//advanced for loop
            for(int n = 0; n < 1; n++) {
                JTextField textField = new JTextField(7);
                inputs[m-1] = textField;
                panelHolder[m][n].add(textField);
            }
        }

        //submit button
        JButton submitBtn = new JButton("Play!");
        panelHolder[5][1].add(submitBtn);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BuilderHandler builderHandler = new BuilderHandler(builder);


                //extract information fr
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
                        if(!element.equals(MoleculeType.ALPHA_L) && !element.equals(MoleculeType.BETA_L)) {
                            if (isStringInt(inputs[1].getText()))
                                moleculeAmount.put(element, Integer.parseInt(inputs[1].getText()));
                            else
                                moleculeAmount.put(element, 100);
                        }
                    }else{
                        if(!element.equals(MoleculeType.ALPHA) && !element.equals(MoleculeType.BETA)) {
                            if (isStringInt(inputs[1].getText()))
                                moleculeAmount.put(element, Integer.parseInt(inputs[1].getText()));
                            else
                                moleculeAmount.put(element, 100);
                        }
                    }
                }

                //pass extracted variables and call controller class
                builderHandler.buildGame(windowWidth, windowHeight, difficultyLevel, atomAmount, moleculeAmount, powerUpAmount, reactionBlockerAmount, isLinear, (LRatioSlider.getValue()* 5)/100.0);
                BuildWindowFactory.getInstance().getDefaultCloseOperation();
                BuildWindowFactory.getInstance().setVisible(false);
                BuildWindowFactory.getInstance().dispose();

                final GameWindowFactory gamePanel = GameWindowFactory.getInstance();

                gamePanel.setWindowHeight(windowHeight);
                gamePanel.setWindowWidth(windowWidth);
                gamePanel.setL((LRatioSlider.getValue() * 5)/100.0);
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
