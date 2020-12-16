package com.company.UI;

import com.company.Domain.Controller.BlenderHandler;
import com.company.Domain.Models.Inventory;
import com.company.Enums.AtomType;
import com.company.Utils.CenterWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlenderWindowFactory extends JFrame {
    public static int windowWidth = 720;
    public static int windowHeight = 360;
    private static BlenderWindowFactory factoryInstance;
    JLabel alphaAmountLable;
    JLabel betaAmountLable;
    JLabel gammaAmountLable;
    JLabel sigmaAmountLable;
    private BlenderHandler blenderHandler;
    private Inventory inventory;
    private JPanel[][] panelHolder;

    public BlenderWindowFactory() {
        inventory = Inventory.getInstance();
        blenderHandler = new BlenderHandler();
    }

    public void render() {
        //factoryInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowWidth, windowHeight);
        this.setTitle("KUVid Blender");
        this.setLayout(new FlowLayout());
        this.setBackground(Color.DARK_GRAY);
        CenterWindow.centerWindow(this);
        JPanel inputPanel = new JPanel();
        int rows = 7;
        int columns = 3;

        inputPanel.setLayout(new GridLayout(rows,columns));

        panelHolder = new JPanel[rows][columns];

        for(int m = 0; m < rows; m++) {
            for(int n = 0; n < columns; n++) {
                panelHolder[m][n] = new JPanel();
                inputPanel.add(panelHolder[m][n]);
            }
        }
        ImageIcon alpha = new ImageIcon(getClass().getResource("Assets/atoms/alpha.png"));
        Image scaledAlpha = alpha.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
        JLabel alphaLabel = new JLabel(new ImageIcon(scaledAlpha));

        ImageIcon beta = new ImageIcon(getClass().getResource("Assets/atoms/beta.png"));
        Image scaledBeta = beta.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
        JLabel betaLabel = new JLabel(new ImageIcon(scaledBeta));

        ImageIcon gamma = new ImageIcon(getClass().getResource("Assets/atoms/gamma.png"));
        Image scaledGamma = gamma.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
        JLabel gammaLabel = new JLabel(new ImageIcon(scaledGamma));

        ImageIcon sigma = new ImageIcon(getClass().getResource("Assets/atoms/sigma.png"));
        Image scaledSigma = sigma.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
        JLabel sigmaLabel = new JLabel(new ImageIcon(scaledSigma));

        panelHolder[0][0].add(alphaLabel);
        alphaAmountLable = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.ALPHA)));
        panelHolder[0][0].add(alphaAmountLable);

        panelHolder[1][0].add(betaLabel);
        betaAmountLable = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.BETA)));
        panelHolder[1][0].add(betaAmountLable);

        panelHolder[2][0].add(gammaLabel);
        gammaAmountLable = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.GAMMA)));
        panelHolder[2][0].add(gammaAmountLable);

        panelHolder[3][0].add(sigmaLabel);
        sigmaAmountLable = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.SIGMA)));
        panelHolder[3][0].add(sigmaAmountLable);



        panelHolder[0][1].add(new JLabel("Blend Atoms!"));

        JButton alphaToBeta = new JButton("+1 Beta (-2 Alpha)");
        panelHolder[1][1].add(alphaToBeta);
        alphaToBeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.blendAtoms(AtomType.ALPHA,AtomType.BETA,2);
                update();
            }

        });

        JButton alphaToGamma = new JButton("+1 Gamma (-3 Alpha)");
        panelHolder[2][1].add(alphaToGamma);
        alphaToGamma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.blendAtoms(AtomType.ALPHA,AtomType.GAMMA,3);
                update();
            }
        });

        JButton alphaToSigma = new JButton("+1 Sigma (-4 Alpha)");
        panelHolder[3][1].add(alphaToSigma);
        alphaToSigma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.blendAtoms(AtomType.ALPHA,AtomType.SIGMA,4);
                update();
            }
        });

        JButton betaToGamma = new JButton("+1 Gamma (-2 Beta)");
        panelHolder[4][1].add(betaToGamma);
        betaToGamma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.blendAtoms(AtomType.BETA,AtomType.GAMMA,2);
                update();
            }
        });

        JButton betaToSigma = new JButton("+1 Sigma (-3 Beta)");
        panelHolder[5][1].add(betaToSigma);
        betaToSigma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.blendAtoms(AtomType.BETA,AtomType.SIGMA,3);
                update();
            }
        });

        JButton gammaToSigma = new JButton("+1 Sigma (-2 Gamma)");
        panelHolder[6][1].add(gammaToSigma);
        gammaToSigma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.blendAtoms(AtomType.GAMMA,AtomType.SIGMA,2);
                update();
            }
        });

        panelHolder[0][2].add(new JLabel("Break Atoms!"));

        JButton betaToAlpha = new JButton("+2 Alpha (-1 Beta)");
        panelHolder[1][2].add(betaToAlpha);
        betaToAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.breakAtoms(AtomType.BETA,AtomType.ALPHA,1);
                update();
            }
        });

        JButton gammaToAlpha = new JButton("+3 Alpha (-1 Gamma)");
        panelHolder[2][2].add(gammaToAlpha);
        gammaToAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.breakAtoms(AtomType.GAMMA,AtomType.ALPHA,1);
                update();
            }
        });

        JButton sigmaToAlpha = new JButton("+4 Alpha (-1 Sigma)");
        panelHolder[3][2].add(sigmaToAlpha);
        sigmaToAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.breakAtoms(AtomType.SIGMA,AtomType.ALPHA,1);
                update();
            }
        });

        JButton gammaToBeta = new JButton("+2 Beta (-1 Gamma)");
        panelHolder[4][2].add(gammaToBeta);
        gammaToBeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.breakAtoms(AtomType.GAMMA,AtomType.BETA,1);
                update();
            }
        });

        JButton sigmaToBeta = new JButton("+3 Beta (-1 Sigma)");
        panelHolder[5][2].add(sigmaToBeta);
        sigmaToBeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.breakAtoms(AtomType.SIGMA,AtomType.BETA,1);
                update();
            }
        });

        JButton sigmaToGamma = new JButton("+2 Gamma (-1 Sigma)");
        panelHolder[6][2].add(sigmaToGamma);
        sigmaToGamma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                blenderHandler.breakAtoms(AtomType.SIGMA,AtomType.GAMMA,1);
                update();
            }
        });

        this.getContentPane().add(inputPanel);
        this.setVisible(true);
    }

    void update(){
        alphaAmountLable.setText(String.valueOf(inventory.getAtomAmount(AtomType.ALPHA)));
        betaAmountLable.setText(String.valueOf(inventory.getAtomAmount(AtomType.BETA)));
        gammaAmountLable.setText(String.valueOf(inventory.getAtomAmount(AtomType.GAMMA)));
        sigmaAmountLable.setText(String.valueOf(inventory.getAtomAmount(AtomType.SIGMA)));
    }

}
