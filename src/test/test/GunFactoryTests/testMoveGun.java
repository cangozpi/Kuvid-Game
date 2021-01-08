package GunFactoryTests;


import com.company.Domain.Controller.BuilderHandler;
import com.company.Domain.Models.AtomFactory;
import com.company.Domain.Models.Builder;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GunFactory;
import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Decorator.*;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.*;
import com.company.UI.BuildWindowFactory;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;



public class testMoveGun {
    //NOTE: if tests do not work when you run the program then you have to do File->ProjectStructure->Modules
    // and then change junit.api and j4test and vintage to compile from test to fix the issue. and use java 1.8

    //variable to hold Atom instances that are generated on each test call
    ArrayList<Atom> initializedAtomInstances = new ArrayList<>();

    //after each test getInstance() should return an instance of Atom class if no Exception is raised
    @After
    public void tearDown() throws Exception {
        initializedAtomInstances.stream().forEach((x) -> {
            switch(x.getAtomType()){
                case ALPHA:
                    assertEquals(x.getClass(), AlphaDecorator.class);
                    break;
                case BETA:
                    assertEquals(x.getClass(), BetaDecorator.class);
                    break;
                case SIGMA:
                    assertEquals(x.getClass(), SigmaDecorator.class);
                    break;
                case GAMMA:
                    assertEquals(x.getClass(), GammaDecorator.class);
                    break;
            }
            assertNotNull(x);
        });

        //clear the list for saving resources
        initializedAtomInstances.clear();
    }


    GunFactory gun;
    GameFactory game;
    // game needs to be initialized in order to create the gun since it uses parameters from other classes
    //initialize the gun instance
    @Before
    public void init() {
        HashMap<AtomType, Integer> inventoryContents = new HashMap<>();
        inventoryContents.put(AtomType.ALPHA,10);                               //initializing the game
        inventoryContents.put(AtomType.BETA,10);
        inventoryContents.put(AtomType.GAMMA,10);
        inventoryContents.put(AtomType.SIGMA,10);

        Builder builder = new Builder();
        builder.buildGame(1180, 720, 1, inventoryContents, true,  0.20);
        game = GameFactory.getInstance();
        game.pauseGame();
        gun = GunFactory.getInstance();

    }

    //returned atom should be initialized correctly with the supplied parameters
    // (i.e Atom should have the same coordinate, velocity, projectileType, isAmmo, height, width).
    @Test
    public void rightEdgeTest(){
        //check btw the passed in parameters and the returned instance's variables
        //valid parameters to initialize Atoms below
        int L = gun.getL();
        int gameWindowWidth = game.getGameWindowWidth();
        double gunWidth = gun.getGunWidth();
        Coordinate oldPosition = new Coordinate(gameWindowWidth - gun.getGunWidth(), 300);
        gun.setPosition(oldPosition);
       // double xCoord = gun.getPosition().getXCoordinate() + 3*L/4;   // setting ammo position according to the gun position as well
       // gun.getAmmo().setXCoordinate(xCoord);
        gun.moveGun(DirectionType.RIGHT);


        //coordinate check shouldn't move
        assertEquals(gun.getPosition(), oldPosition);



    }

    //Returned atom should be an instance of the passed in atomType’s corresponding concrete AtomDecorator class.
    // (i.e if passed in atomType is Alpha_atom then, AlphaDecorator instance should be returned).
    @Test
    public void leftEdgeTest(){
        //check btw the passed in parameters and the returned instance's variables
        //valid parameters to initialize Atoms below
        int L = gun.getL();
        double gunWidth = gun.getGunWidth();
        Coordinate oldPosition = new Coordinate(0, 300);
        gun.setPosition(oldPosition);
        // double xCoord = gun.getPosition().getXCoordinate() + 3*L/4;   // setting ammo position according to the gun position as well
        // gun.getAmmo().setXCoordinate(xCoord);
        gun.moveGun(DirectionType.LEFT);
        //coordinate check shouldn't move
        assertEquals(gun.getPosition(), oldPosition);



    }


    @Test
    public void movementAmountTest(){
        int L = gun.getL();
        Coordinate oldPosition = gun.getPosition();                             // before movement
        Coordinate newPosition = oldPosition;
        newPosition.setXCoordinate(newPosition.getXCoordinate() + L/30);        // should be after moving right

        gun.moveGun(DirectionType.RIGHT);
        assertEquals(gun.getPosition(),newPosition);

        oldPosition = gun.getPosition();                                        // before left movement
        newPosition = oldPosition;
        newPosition.setXCoordinate(newPosition.getXCoordinate() - L/30);        // should be after moving left

        assertEquals(gun.getPosition(),newPosition);
    }


    @Test
    public void ammoMovedTest(){

        int L = gun.getL();


        gun.moveGun(DirectionType.RIGHT);
        double newAmmoCoord = gun.getPosition().getXCoordinate() + 3*L/4;
        assertEquals(gun.getAmmo().getXCoordinate(),newAmmoCoord,1);      //delta 1 for rounding, double -> int

        gun.moveGun(DirectionType.LEFT);
        newAmmoCoord = gun.getPosition().getXCoordinate() + 3*L/4;
        assertEquals(gun.getAmmo().getXCoordinate(),newAmmoCoord,1);      //delta 1 for rounding, double -> int

    }



    @Test
    public void delegatedToGameTest(){

        int L = gun.getL();

        gun.moveGun(DirectionType.RIGHT);
        double newAmmoCoord = gun.getPosition().getXCoordinate() + 3*L/4;
        assertEquals(gun.getAmmo().getXCoordinate(),newAmmoCoord,1);      //delta 1 for rounding, double -> int
        assertEquals(gun.getPosition(),game.getGunPosition());
        assertEquals(gun.getAmmo().getCoordinate(),game.getAmmo().getCoordinate());


        gun.moveGun(DirectionType.LEFT);
        newAmmoCoord = gun.getPosition().getXCoordinate() + 3*L/4;
        assertEquals(gun.getAmmo().getXCoordinate(),newAmmoCoord,1);      //delta 1 for rounding, double -> int
        assertEquals(gun.getPosition(),game.getGunPosition());
        assertEquals(gun.getAmmo().getCoordinate(),game.getAmmo().getCoordinate());

    }














}