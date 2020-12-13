package com.company.UI.Objects;

import com.company.Domain.Controller.BlenderHandler;
import com.company.Domain.Controller.MoveShooterHandler;
import com.company.Domain.Controller.RotateGunHandler;
import com.company.Domain.Controller.ShooterHandler;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GunFactory;
import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Models.Projectile.ReactionBlocker;
import com.company.Domain.Observer.GunObserver;
import com.company.Domain.Observer.IGunListener;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.DirectionType;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ReactionBlockerType;
import com.company.UI.Objects.Observer.GameObserver;
import com.company.UI.Objects.Observer.IGameListener;
import com.company.Utils.CenterWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameWindowFactory extends JFrame implements IGameListener, KeyListener, ActionListener,IGunListener {
/*
    Main UI Frame, uses Singleton Pattern
 */
    public static int windowWidth = 852;
    public static int windowHeight = 480;
    public static int L = windowHeight / 10;
    private MoveShooterHandler moveShooterHandler;
    private RotateGunHandler rotateGunHandler;
    private ShooterHandler shooterHandler;
    private BlenderHandler blenderHandler;

    //list holding JPanel's to be drawn
    private ArrayList<GameObject> objectList = new ArrayList<>();

    private static GameWindowFactory factoryInstance; //holds single instance variable of this class

    private GameWindowFactory()  {
        moveShooterHandler = new MoveShooterHandler();
        rotateGunHandler = new RotateGunHandler();
        blenderHandler = new BlenderHandler();
        shooterHandler = new ShooterHandler();

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
        CenterWindow.centerWindow(this);
        //Dark mode for aesthetic purposes
        factoryInstance.getContentPane().setBackground(Color.DARK_GRAY);
        KeyListener blendListener = new BlenderHandler();
        addKeyListener(blendListener);

        GunObserver gunObserver = GunFactory.getInstance();
        gunObserver.addListener(this);

        draw();
        this.addKeyListener(this);
        factoryInstance.setVisible(true);
    }

    public synchronized void draw(){

        this.getContentPane().removeAll();
        GameObject bgObject = new BackgroundObject(new Coordinate(0, -10), GameWindowFactory.getInstance().windowWidth, GameWindowFactory.getInstance().windowHeight, "kuvid_bc.png");
        this.addToObjectList(bgObject);

        //draw JPanel elements from list
                for(GameObject element: objectList){
                    element.draw();

                }


            this.revalidate();
            this.repaint();
    }

    public void addToObjectList(GameObject object){
        objectList.add(object);
    }

    public void clearObjectList(){//empty object list
        objectList = new ArrayList<GameObject>();
    }

    @Override
    public void positionChanged(List<Molecule> moleculeList, List<Projectile> projectileList, List<ReactionBlocker> reactionBlockerList, List<PowerUp> powerUpList) {
        //empty objectList before re-adding GameObjects from zero
        clearObjectList();

        //add GameObjects to the objectList
        for (Molecule molecule : moleculeList) {
            // create ui object from data in projectile
            //instantiate object accordingly

            GameObject currentObject;
            int width = molecule.getWidth();
            int height = molecule.getHeight();

            if (molecule.getMoleculeType() == MoleculeType.ALPHA_1) {
                currentObject = new AlphaOneMoleculeObject(molecule.getCoordinate(), width, height, 0);
            } else if (molecule.getMoleculeType() == MoleculeType.ALPHA_2) {
                currentObject = new AlphaTwoMoleculeObject(molecule.getCoordinate(), width, height, 0);
            } else if (molecule.getMoleculeType() == MoleculeType.BETA_1) {
                currentObject = new BetaOneMoleculeObject(molecule.getCoordinate(), width, height, 0);
            } else if (molecule.getMoleculeType() == MoleculeType.BETA_2) {
                currentObject = new BetaTwoMoleculeObject(molecule.getCoordinate(), width, height, 0);
            } else if (molecule.getMoleculeType() == MoleculeType.GAMMA) {
                currentObject = new GammaMoleculeObject(molecule.getCoordinate(), width, height, 0);
            } else  {
                currentObject = new SigmaMoleculeObject(molecule.getCoordinate(), width, height, 0);
            }

            addToObjectList(currentObject);
        }


        for (Projectile projectile : projectileList) {


        }


        for (ReactionBlocker reactionBlocker : reactionBlockerList) {
            reactionBlocker.getCoordinate();
            GameObject currentObject;
            if (reactionBlocker.getReactionBlockerType() == ReactionBlockerType.ALPHA_B) {
                currentObject = new AlphaBlockerObject(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), 0);
            } else if (reactionBlocker.getReactionBlockerType() == ReactionBlockerType.BETA_B) {
                currentObject = new BetaBlockerObject(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), 0);
            } else if (reactionBlocker.getReactionBlockerType() == ReactionBlockerType.GAMMA_B) {
                currentObject = new GammaBlockerObject(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), 0);
            } else{
                currentObject = new SigmaBlockerObject(reactionBlocker.getCoordinate(), reactionBlocker.getWidth(), reactionBlocker.getHeight(), 0);
            }
            // create ui object from data in projectile
            addToObjectList(currentObject);
        }
        for (PowerUp powerUp : powerUpList) {
            Coordinate coordinate = powerUp.getCoordinate();

            // create ui object from data in projectile
            GameObject currentObject;
            int width = powerUp.getWidth();
            int height = powerUp.getHeight();

            if (powerUp.getPowerUpType() == PowerUpType.ALPHA) {
                currentObject = new AlphaBlockerObject(coordinate, width, height, 0);
            } else if (powerUp.getPowerUpType() == PowerUpType.BETA) {
                currentObject = new BetaBetaPowerUpObject(coordinate, width, height, 0);
            } else if (powerUp.getPowerUpType() == PowerUpType.GAMMA) {
                currentObject = new GammaBetaPowerUpObject(coordinate, width, height, 0);
            } else{
                currentObject = new SigmaBlockerObject(coordinate, width, height, 0);
           }

            addToObjectList(currentObject);
        }
    }

    @Override
    public void gunMoved(Coordinate coord, int angle, Projectile projectile){
        clearObjectList();
        GameObject lol = new GunObject(coord,  (int)(GameWindowFactory.L / 2), GameWindowFactory.L, "shooter.png", angle);
        addToObjectList(lol);
        this.draw();
    }

    @Override
    public void keyTyped(KeyEvent e) {// Do not implement this

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());


        if(e.getKeyCode() == 37){ //Left Arrow
            leftArrow();
        }

        if(e.getKeyCode() == 39){ //Right Arrow
            rightArrow();
        }

        if(e.getKeyCode() == 38){ //Up Arrow
            upArrow();
        }

        if(e.getKeyCode() == 65){ //A
            rotateLeft();
        }

        if(e.getKeyCode() == 68){ //D
            rotateRight();
        }

        if(e.getKeyCode() == 67){ //C
            selectAtom();
        }

        if(e.getKeyCode() == 80){ //P
            pause();
        }

        if(e.getKeyCode() == 66){ //B
            breakOrBlend();
        }

        if(e.getKeyCode() == 66){ //B
            breakOrBlend();
        }

        if(e.getKeyCode() == 82){ //R
            resume();
        }

    }

    //ActionPerformed actions below
    public void upArrow(){
        shooterHandler.shootGun();
    }

    public void leftArrow(){
        moveShooterHandler.moveGun(DirectionType.LEFT);
    }

    public void rightArrow(){
        moveShooterHandler.moveGun(DirectionType.RIGHT);
    }

    public void rotateLeft(){
        rotateGunHandler.rotateGun(DirectionType.ANTICLOCKWISE);
    }

    public void rotateRight(){
        rotateGunHandler.rotateGun(DirectionType.CLOCKWISE);
    }

    public void selectAtom(){
        rotateGunHandler.rotateGun(DirectionType.CLOCKWISE);
    }

    public void breakOrBlend(){

    }

    public void resume(){
        GameFactory.getInstance().resumeGame();
    }

    public void pause(){
        GameFactory.getInstance().pauseGame();
    }


    @Override
    public void keyReleased(KeyEvent e) { // Do not implement this

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       
    }

    @Override
    public void paintComponents(Graphics graphics) {
        super.paintComponents(graphics);
    }
}
