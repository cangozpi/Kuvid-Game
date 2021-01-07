package AtomFactoryTests;


import com.company.Domain.Models.AtomFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;


public class testAtomFactory {
    //NOTE: if tests do not work when you run the program then you have to do File->ProjectStructure->Modules
    // and then change junit.api and j4test to compile to fix the issue. and use java 1.8

    //denotes that the annotated method will be executed before each test method
    @BeforeEach
    //@DisplayName("A special test case")
    public void beforeClass() throws Exception {

    }

    //denotes that the annotated method will be executed after each test method
    @AfterEach
    public void afterClass() throws Exception {

    }

    //returned atom should be initialized correctly with the supplied parameters
    // (i.e Atom should have the same coordinate, velocity, projectileType, isAmmo, height, width).
    @Test
    public void initializeWithCorrectParameters(){
        //valid parameters to initialize Atoms below
        Coordinate[] coordinates = {new Coordinate(0,0), new Coordinate(1,-1), new Coordinate(-2,2), new Coordinate(3,3)};
        Velocity[] velocitys = {new Velocity(10, 1), new Velocity(20, 2), new Velocity(30, 3.3), new Velocity(40, 4.4)};
        IProjectileType[] projectileTypes = {AtomType.ALPHA, AtomType.BETA, AtomType.GAMMA, AtomType.SIGMA};
        boolean[] isAmmos = {true, false, true, false};
        int[] heights = {1, 2, 3, 4};
        int[] widths = {4,3,2,1};

        //check btw the passed in parameters and the returned instance's variables
        for(int i = 0; i < 4; i++){
            Atom testInstance = new AtomFactory().getInstance(coordinates[i], velocitys[i], projectileTypes[i], isAmmos[i], heights[i], widths[i]);
            //check coordinate
            assertEquals(testInstance.getCoordinate(), coordinates[i]);
            //check velocity
            assertEquals(testInstance.getVelocity(), velocitys[i]);
            //check projectileType(a.k.a atomType)
            assertEquals(testInstance.getAtomType(), projectileTypes[i]);
            //check isAmmo
            assertEquals(testInstance.isAmmo(), isAmmos[i]);
            //check height
            assertEquals(testInstance.getHeight(), heights[i]);
            //check width
            assertEquals(testInstance.getWidth(), widths[i]);
        }


    }

  /*  @Test
    @DisplayName("A special test case")
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    public void name(String candidate) {
        *//*assertEquals(GameFactory.class, GameFactory.getInstance().getClass());
        assumeFalse(5 < 1);*//*

    }*/

}