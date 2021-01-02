package com.company.UI.Objects;

import com.company.Domain.Controller.*;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.Projectile.*;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.*;
import com.company.UI.Observer.GameObserver;
import com.company.UI.Observer.IGameListener;
import com.company.Utils.CenterWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class GameWindowFactory extends JFrame implements IGameListener,  ActionListener {
/*
    Main UI Frame, uses Singleton Pattern
 */
    public static int windowWidth = 852;
    public static int windowWidthExtended = 1100;
    public static int windowHeight = 480;
    public static int L = windowHeight / 10;

    GameObject gunObj; //reference to gunObject that is drawn

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
        factoryInstance.setSize(windowWidthExtended , windowHeight);
        factoryInstance.setTitle("Karel Kuvid");
        factoryInstance.setLayout(null);
        CenterWindow.centerWindow(this);

        JPanel StatBoard = new JPanel();
        StatBoard.setBackground(Color.BLACK);
        StatBoard.setOpaque(false);
        StatBoard.setBounds(windowWidth,0,windowWidthExtended,windowHeight);

        //Dark mode for aesthetic purposes
        factoryInstance.getContentPane().setBackground(Color.DARK_GRAY);

        //blender listener
        KeyListener blenderListener = new BlenderHandler();
        addKeyListener(blenderListener);

        //menu listener
        KeyListener menuListener = new MenuHandler();
        addKeyListener(menuListener);

        //move shooter listener
        KeyListener moveShooterListener = new MoveShooterHandler();
        addKeyListener(moveShooterListener);

        //rotate shooter listener
        KeyListener rotateGunListener  = new RotateGunHandler();
        addKeyListener(rotateGunListener);

        //select atom listener
        KeyListener selectAtomListener  = new SelectAtomHandler();
        addKeyListener(selectAtomListener);

        //shoot  listener
        KeyListener shootListener  = new ShooterHandler();
        addKeyListener(shootListener);


        //select powerUp listener     mouse listener
       // KeyListener selectPowerUpListener  = new SelectPowerUpHandler();
       // addKeyListener(selectPowerUpListener);

        //shoot listener




        GameObserver gameObserver = GameFactory.getInstance();
        gameObserver.addListener(this);


        draw();

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
        objectList = new ArrayList<>();
    }

    @Override
    public void positionChanged(ArrayList<Molecule> moleculeList, ArrayList<Projectile> projectileFromGunList,
                                ArrayList<ReactionBlocker> reactionBlockerList,
                                ArrayList<PowerUp> powerUpList, Coordinate gunPosition, int gunAngle, Projectile ammo ) {
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
                currentObject = new PowerUpObject(powerUp.getCoordinate(),0,powerUp.getProjectileType());
                addToObjectList(currentObject);
            }
        }

        if(!projectileFromGunList.isEmpty()) {
            for (Projectile projectile : projectileFromGunList) {
                // create ui object from data in projectile
                //instantiate object accordingly
                GameObject currentObject;
                switch(projectile.getProjectileType().toString()){
                    case AtomType.ALPHA_atom:
                        currentObject = new AtomObject(projectile.getCoordinate(),0,AtomType.ALPHA);
                        addToObjectList(currentObject);
                        break;
                    case AtomType.BETA_atom:
                        currentObject = new AtomObject(projectile.getCoordinate(),0,AtomType.BETA);
                        addToObjectList(currentObject);
                        break;
                    case AtomType.GAMMA_atom:
                        currentObject = new AtomObject(projectile.getCoordinate(),0,AtomType.GAMMA);
                        addToObjectList(currentObject);
                        break;
                    case AtomType.SIGMA_atom:
                        currentObject = new AtomObject(projectile.getCoordinate(),0,AtomType.SIGMA);
                        addToObjectList(currentObject);
                        break;
                    case PowerUpType.ALPHA_powerUp:
                        currentObject = new PowerUpObject(projectile.getCoordinate(),0,PowerUpType.ALPHA);
                        addToObjectList(currentObject);
                        break;
                    case PowerUpType.BETA_powerUp:
                        currentObject = new PowerUpObject(projectile.getCoordinate(),0,PowerUpType.BETA);
                        addToObjectList(currentObject);
                        break;
                    case PowerUpType.GAMMA_powerUp:
                        currentObject = new PowerUpObject(projectile.getCoordinate(),0,PowerUpType.GAMMA);
                        addToObjectList(currentObject);
                        break;
                    case PowerUpType.SIGMA_powerUp:
                        currentObject = new PowerUpObject(projectile.getCoordinate(),0,PowerUpType.SIGMA);
                        addToObjectList(currentObject);
                        break;

                }





            }

        }





            //re add the gunObj to its new position
            gunObj = new GunObject(gunPosition, gunAngle);
            addToObjectList(gunObj);
            GameObject currentObject;

            if(Pattern.matches(".*atom$", ammo.getProjectileType().toString())) {

                //instantiate corresponding atom object
                currentObject = new AtomObject(ammo.getCoordinate(), 0, ammo.getProjectileType());

            }else{

                //instantiate corresponding powerup object

                currentObject = new PowerUpObject(ammo.getCoordinate(),0,ammo.getProjectileType());
            }
        addToObjectList(currentObject);
        this.draw();

    }





    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       
    }

    @Override
    public void paintComponents(Graphics graphics) {
        super.paintComponents(graphics);
    }

    public ArrayList<GameObject> getObjectList() {
        return objectList;
    }

    public void setObjectList(ArrayList<GameObject> objectList) {
        this.objectList = objectList;
    }
}
