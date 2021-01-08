package AtomFactoryTests;


import com.company.Domain.Models.AtomFactory;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Decorator.*;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;


public class testAtomFactory {
    //NOTE: if tests do not work when you run the program then you have to do File->ProjectStructure->Modules
    // and then change junit.api and j4test to compile to fix the issue. and use java 1.8

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
        });

        //clear the list for saving resources
        initializedAtomInstances.clear();
    }

    //returned atom should be initialized correctly with the supplied parameters
    // (i.e Atom should have the same coordinate, velocity, projectileType, isAmmo, height, width).
    @Test
    public void initializeWithCorrectParameters(){
        //check btw the passed in parameters and the returned instance's variables
        //valid parameters to initialize Atoms below
        Coordinate[] coordinates = {new Coordinate(0,0), new Coordinate(1,-1), new Coordinate(-2,2), new Coordinate(3,3)};
        Velocity[] velocitys = {new Velocity(10, 1), new Velocity(20, 2), new Velocity(30, 3.3), new Velocity(40, 4.4)};
        IProjectileType[] projectileTypes = {AtomType.ALPHA, AtomType.BETA, AtomType.GAMMA, AtomType.SIGMA};
        boolean[] isAmmos = {true, false, true, false};
        int[] heights = {1, 2, 3, 4};
        int[] widths = {4,3,2,1};

        //do the checks on parameters
        for(int i = 0; i < 4; i++){
            Atom testInstance = new AtomFactory().getInstance(coordinates[i], velocitys[i], projectileTypes[i], isAmmos[i], heights[i], widths[i]);
            //add to list to check whether it stays as valid at the end of the test call
            initializedAtomInstances.add(testInstance);

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

    //Returned atom should be an instance of the passed in atomType’s corresponding concrete AtomDecorator class.
    // (i.e if passed in atomType is Alpha_atom then, AlphaDecorator instance should be returned).
    @Test
    public void correspondingAtomDecoratorGivenAtomType(){
       Arrays.stream(AtomType.values()).forEach((x) -> {
           Atom testInstance = new AtomFactory().getInstance(new Coordinate(0,0), new Velocity(10, 1)
           , x, true, 1, 1);

           //check for the correct atomType's corresponding AtomDecorator class
           switch (x){
               case ALPHA:
                   assertTrue(testInstance instanceof AlphaDecorator);
                   break;
               case BETA:
                   assertTrue(testInstance instanceof BetaDecorator);
                   break;
               case SIGMA:
                   assertTrue(testInstance instanceof SigmaDecorator);
                   break;
               case GAMMA:
                   assertTrue(testInstance instanceof GammaDecorator);
                   break;
           }

           //add testInstance for @After check
           initializedAtomInstances.add(testInstance);
       });
    }

    //check AlphaDecorator's aspects
    @Test
    public void AlphaDecoratorTest(){
        AtomFactory factoryInstance = new AtomFactory();
        //initialize for 10 instances to check randomness
        ArrayList<AtomDecorator> instances = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            instances.add((AtomDecorator) factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                    , AtomType.ALPHA, true, 1, 1));
        }

        //check parameters of each instance instantiated
        instances.stream().forEach((x) -> {
            //extract parameters from instance
            double stabilityConstant = x.getStabilityConstant();
            double protons = x.getProtons();
            double neutrons = x.getNeutrons();
            double efficiency = x.getEfficiency();

            //stability Constant should be 0.5
            assertEquals(0.85 ,stabilityConstant,0);
            //protons should be 8
            assertEquals(8, protons, 0);
            //neutrons should be either 7 | 8 | 9
            assertEquals(8, neutrons, 1);
            //efficiency should be (1 – ((neutrons – protons) / protons) * stabilityConstant)
            assertEquals((1 - ((neutrons - protons) / protons) * stabilityConstant), efficiency, 0);
        });


        //add testInstance for @After check
        instances.stream().forEach((x) -> initializedAtomInstances.add(x));
    }

    //check BetaDecorator's aspects
    @Test
    public void BetaDecoratorTest(){
        AtomFactory factoryInstance = new AtomFactory();
        //initialize for 10 instances to check randomness
        ArrayList<AtomDecorator> instances = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            instances.add((AtomDecorator) factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                    , AtomType.BETA, true, 1, 1));
        }

        //check parameters of each instance instantiated
        instances.stream().forEach((x) -> {
            //extract parameters from instance
            double stabilityConstant = x.getStabilityConstant();
            double protons = x.getProtons();
            double neutrons = x.getNeutrons();
            double efficiency = x.getEfficiency();

            //stability Constant should be 0.9
            assertEquals(0.9 ,stabilityConstant,0);
            //protons should be 16
            assertEquals(16, protons, 0);
            //neutrons should be either 15 | 16 | 17 | 18 | 21
            assertThat(protons, anyOf(is(15.0), is(16.0), is(17.0), is(18.0), is(21.0)));
            //efficiency should be stabilityConstant – ((0.5 * (neutrons  - protons)) / protons)
            assertEquals(stabilityConstant - ((0.5 * (neutrons  - protons)) / protons), efficiency, 0);
        });


        //add testInstance for @After check
        instances.stream().forEach((x) -> initializedAtomInstances.add(x));
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
