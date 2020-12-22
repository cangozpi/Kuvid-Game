package com.company.UI.Objects;

import com.company.Domain.Controller.*;
import com.company.Domain.Models.AtomSelectorFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GunFactory;
import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Observer.GunObserver;
import com.company.Domain.Observer.IGunListener;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.*;
import com.company.UI.Observer.GameObserver;
import com.company.UI.Observer.IGameListener;
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
    private SelectAtomHandler selectAtomHandler;
    GameObject gunObj; //reference to gunObject that is drawn

    //list holding JPanel's to be drawn
    private ArrayList<GameObject> objectList = new ArrayList<>();

    private static GameWindowFactory factoryInstance; //holds single instance variable of this class

    private GameWindowFactory()  {
        moveShooterHandler = new MoveShooterHandler();
        rotateGunHandler = new RotateGunHandler();
        blenderHandler = new BlenderHandler();
        shooterHandler = new ShooterHandler();
        selectAtomHandler = new SelectAtomHandler(AtomSelectorFactory.getInstance());

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
        GameObserver gameObserver = GameFactory.getInstance();
        gameObserver.addListener(this);


        draw();
        this.addKeyListener(this);
        factoryInstance.setVisible(true);
    }

    public synchronized void draw(){

        this.getContentPane().removeAll();
        GameObject bgObject = new BackgroundObject(new Coordinate(0, -10));
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
    public void positionChanged(ArrayList<Molecule> moleculeList, ArrayList<Atom> atomList, ArrayList<PowerUp> shotPowerUpList,
                                ArrayList<ReactionBlocker> reactionBlockerList, ArrayList<PowerUp> powerUpList, Coordinate gunPosition, int gunAngle) {
        //empty objectList before re-adding GameObjects from zero
        clearObjectList();

        //add GameObjects to the objectList
        if(!moleculeList.isEmpty()) {
            for (Molecule molecule : moleculeList) {
                // create ui object from data in projectile
                //instantiate object accordingly
                GameObject currentObject;
                currentObject = new MoleculeObject(molecule.getCoordinate(),0, molecule.getMoleculeType());
                addToObjectList(currentObject);
            }
        }

        if(!reactionBlockerList.isEmpty()) {
            for (ReactionBlocker reactionBlocker : reactionBlockerList) {
                GameObject currentObject;
                currentObject = new ReactionBlockerObject(reactionBlocker.getCoordinate(), 0,reactionBlocker.getReactionBlockerType());
                // create ui object from data in projectile
                addToObjectList(currentObject);
            }
        }

        if(!powerUpList.isEmpty()) {
            for (PowerUp powerUp : powerUpList) {
                // create ui object from data in projectile
                GameObject currentObject;
                currentObject = new PowerUpObject(powerUp.getCoordinate(),0,powerUp.getPowerUpType());
                addToObjectList(currentObject);
            }
        }

        if(!atomList.isEmpty()) {
            for (Atom atom : atomList) {
                // create ui object from data in projectile
                //instantiate object accordingly
                GameObject currentObject;

                currentObject = new AtomObject(atom.getCoordinate(),0,atom.getAtomType());
                addToObjectList(currentObject);
            }

        }

        if(!shotPowerUpList.isEmpty()) {
            for (PowerUp powerUp : shotPowerUpList) {
                // create ui object from data in projectile
                //instantiate object accordingly
                GameObject currentObject;
                currentObject = new PowerUpObject(powerUp.getCoordinate(),0,powerUp.getPowerUpType());
                addToObjectList(currentObject);
            }
        }
        gunObj = new GunObject(gunPosition, gunAngle);
        addToObjectList(gunObj);
        this.draw();
    }

    @Override
    public void gunMoved(Coordinate coord, int angle, Atom atom, PowerUp powerUp){
        //clearObjectList();
        //clear Gun From UI frame
        this.remove(gunObj);

            for (GameObject element : (ArrayList<GameObject>) objectList.clone()) {
                if (element instanceof GunObject) {
                    objectList.remove(element);
                }
            }

        //re add the gunObj to its new position
        gunObj = new GunObject(coord, angle);
        addToObjectList(gunObj);

        if(atom != null){
            GameObject currentObject;
            //instantiate corresponding atom object
            currentObject = new AtomObject(atom.getCoordinate(),0,atom.getAtomType());
            addToObjectList(currentObject);
        }
        if(powerUp != null){
            GameObject currentObject;
            currentObject = new PowerUpObject(powerUp.getCoordinate(),0,powerUp.getPowerUpType());
            addToObjectList(currentObject);
        }
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
        selectAtomHandler.selectAtom();
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
