package test.GunFactoryTests;

import com.company.Domain.Models.AtomFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.GunFactory;
//import com.company.Domain.Models.GunFactory.*;
import com.company.Domain.Utility.Coordinate;

import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.DirectionType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class rotateGunTest {
    // CAN YÜKSELOĞLU TESTS

    GunFactory gunInstance;
    //Requirements game set up and a loaded atom.
    //Modifies guns angle
    @Before
    public void setup(){
        GameFactory gameFactory = GameFactory.getInstance();
        gameFactory.setGameWindowHeight(700);
        gameFactory.setGameWindowWidth(400);
        gameFactory.setL(70);
        gunInstance = GunFactory.getInstance();
        AtomFactory atomFactory = new AtomFactory();
        gunInstance.loadGun(atomFactory.getInstance(new Coordinate(0,0), new Velocity(0,0), AtomType.SIGMA, true,(int)(gameFactory.getL()/10),(int)(gameFactory.getL()/10)));
    }

    @Test
    public void clockwiseChecks() {
        GunFactory gunFactory = GunFactory.getInstance();
        gunFactory.rotateGun(DirectionType.CLOCKWISE);
        assertTrue(gunFactory.getAngle() > 10);
    }
    @Test
    public void antiClockwiseChecks(){
        GunFactory gunFactory = GunFactory.getInstance();
        gunFactory.rotateGun(DirectionType.ANTICLOCKWISE);
        assertTrue(gunFactory.getAngle() < 170);
    }
    @Test
    public void gunPosClockwise() {
        GunFactory gunFactory = GunFactory.getInstance();
        Coordinate oldPos = gunFactory.getPosition();
        gunFactory.rotateGun(DirectionType.CLOCKWISE);
        Coordinate newPos = gunFactory.getPosition();
        assertTrue(gunFactory.getAngle() > 10);
        assertTrue(oldPos.getXCoordinate() != newPos.getXCoordinate()); //TODO New position calculation
    }
    @Test
    public void gunPosAntiClockwise() {
        GunFactory gunFactory = GunFactory.getInstance();
        Coordinate oldPos = gunFactory.getPosition();
        gunFactory.rotateGun(DirectionType.ANTICLOCKWISE);
        Coordinate newPos = gunFactory.getPosition();
        assertTrue(gunFactory.getAngle() < 170);
        assertTrue(oldPos.getXCoordinate() != newPos.getXCoordinate()); //TODO New position calculation
    }
    @Test
    public void atom() {
        GunFactory gunFactory = GunFactory.getInstance();
        Coordinate oldPos = gunInstance.getLeftistPointOfTheGUn();
        gunFactory.rotateGun(DirectionType.CLOCKWISE);
        Coordinate newPos = gunInstance.getLeftistPointOfTheGUn();
        assertTrue(gunFactory.getAngle() > 10);
        assertTrue(oldPos.getXCoordinate() != newPos.getXCoordinate()); // TODO
    }
}




