package test.GunFactoryTests;


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

    // BATU HELVACIOĞLU TESTS

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
        HashMap<ShieldType,Integer> shieldAmount = new HashMap<>();
        Builder builder = new Builder();
        builder.buildGame(1180, 720, 1, inventoryContents, shieldAmount, true,  0.20);
        game = GameFactory.getInstance();
        game.pauseGame();
        gun = GunFactory.getInstance();

    }


    @Test
    public void rightEdgeTest(){

        int L = gun.getL();
        int gameWindowWidth = game.getGameWindowWidth();
        double gunWidth = gun.getGunWidth();
        Coordinate oldPosition = new Coordinate(gameWindowWidth - gun.getGunWidth(), 300);
        gun.setPosition(oldPosition);

        gun.moveGun(DirectionType.RIGHT);


        //coordinate check shouldn't move
        assertEquals(gun.getPosition(), oldPosition);



    }


    @Test
    public void leftEdgeTest(){

        int L = gun.getL();
        double gunWidth = gun.getGunWidth();
        Coordinate oldPosition = new Coordinate(0, 300);
        gun.setPosition(oldPosition);

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
        //  for some reason this test fails on the first run but works after that.
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