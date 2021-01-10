package AtomFactoryTests;


import com.company.Domain.Models.AtomFactory;
import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Decorator.*;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class testAtomFactory {
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


    // global variable
    AtomFactory factoryInstance;

    //initialize common parameters before Each test
    @Before
    public void init() {
        factoryInstance = new AtomFactory();
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
            Atom testInstance = factoryInstance.getInstance(coordinates[i], velocitys[i], projectileTypes[i], isAmmos[i], heights[i], widths[i]);
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
           Atom testInstance = factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
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
    public void alphaDecoratorTest(){
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
    public void betaDecoratorTest(){
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


    //check GammaDecorator's aspects
    @Test
    public void gammaDecoratorTest(){
        //initialize for 10 instances to check randomness
        ArrayList<AtomDecorator> instances = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            instances.add((AtomDecorator) factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                    , AtomType.GAMMA, true, 1, 1));
        }

        //check parameters of each instance instantiated
        instances.stream().forEach((x) -> {
            //extract parameters from instance
            double stabilityConstant = x.getStabilityConstant();
            double protons = x.getProtons();
            double neutrons = x.getNeutrons();
            double efficiency = x.getEfficiency();

            //stability Constant should be 0.8
            assertEquals(0.8 ,stabilityConstant,0);
            //protons should be 32
            assertEquals(32, protons, 0);
            //neutrons should be either 29 | 32 | 33
            assertThat(protons, anyOf(is(29.0), is(32.0), is(33.0)));
            //efficiency should be stabilityConstant + ((neutrons – protons)  / (2 * protons))
            assertEquals(stabilityConstant + ((neutrons - protons)  / (2 * protons)), efficiency, 0);
        });


        //add testInstance for @After check
        instances.stream().forEach((x) -> initializedAtomInstances.add(x));
    }

    //check SigmaDecorator's aspects
    @Test
    public void sigmaDecoratorTest(){
        //initialize for 10 instances to check randomness
        ArrayList<AtomDecorator> instances = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            instances.add((AtomDecorator) factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                    , AtomType.SIGMA, true, 1, 1));
        }

        //check parameters of each instance instantiated
        instances.stream().forEach((x) -> {
            //extract parameters from instance
            double stabilityConstant = x.getStabilityConstant();
            double protons = x.getProtons();
            double neutrons = x.getNeutrons();
            double efficiency = x.getEfficiency();

            //stability Constant should be 0.7
            assertEquals(0.7 ,stabilityConstant,0);
            //protons should be 64
            assertEquals(64, protons, 0);
            //neutrons should be either 29 | 32 | 33
            assertThat(protons, anyOf(is(63.0), is(64.0), is(67.0)));
            //efficiency should be (1 + stabilityConstant) / 2 + ((neutrons – protons) / protons)
            assertEquals((1 + stabilityConstant) / 2 + ((neutrons - protons) / protons), efficiency, 0);
        });


        //add testInstance for @After check
        instances.stream().forEach((x) -> initializedAtomInstances.add(x));
    }


    //Passing atomType anything other than, ALPHA_atom, BETA_atom or GAMMA_atom should always proceed as
    // if SIGMA_atom was passed in as a parameter.(i.e should return an instance of SigmaDecorator class)
    @Test
    public void atomTypeEdgeCase(){
        //pass SIGMA_atom
        Atom instance1 = factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                , AtomType.SIGMA, true, 1, 1);
        assertEquals(AtomType.SIGMA, instance1.getAtomType());

        //pass another enum that implements IProjectileType other than AtomType
        Atom instance2 = factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                , PowerUpType.ALPHA, true, 1, 1);
        assertEquals(AtomType.SIGMA, instance1.getAtomType());

        Atom instance3 = factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                , ReactionBlockerType.SIGMA_B, true, 1, 1);
        assertEquals(AtomType.SIGMA, instance1.getAtomType());

        Atom instance4 = factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                , MoleculeType.ALPHA, true, 1, 1);
        assertEquals(AtomType.SIGMA, instance1.getAtomType());


        //add testInstance for @After check
        initializedAtomInstances.add(instance1);
        initializedAtomInstances.add(instance2);
        initializedAtomInstances.add(instance3);
        initializedAtomInstances.add(instance4);
    }


    //check on crucial implementation specific Exceptions that can be raised
    @Test
    public void exceptionChecks(){
        //passing null should raise null pointer exception due to switch implementation
        assertThrows(NullPointerException.class, () -> {
            factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                    , null, true, 1, 1);
        });
    }


    //Check that neutrons of the returned Atom instances vary randomly.
    @Test
    public void neutronRandomnessTest(){
        //idea is to generate two arrays of size many instances and check their neutron numbers in the same order
        // as they are initialize assume randomness if not all of the corresponding two has the same neutrons
        int size = 20;
        ArrayList<AtomDecorator> list1 = new ArrayList<>();
        ArrayList<AtomDecorator> list2 = new ArrayList<>();

        //initialize the first list
        for(int i = 0; i < size; i++){
            AtomDecorator instance = (AtomDecorator) factoryInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                    , AtomType.SIGMA, true, 1, 1);
            list1.add(instance);
        }

        //initialize the second list with a new instance of AtomFactory
        AtomFactory newInstance = new AtomFactory();
        for(int i = 0; i < size; i++){
            AtomDecorator instance = (AtomDecorator) newInstance.getInstance(new Coordinate(0,0), new Velocity(10, 1)
                    , AtomType.SIGMA, true, 1, 1);
            list2.add(instance);
        }

        //compare the corresponding array elements neutron numbers wrt their initialization order
        int sameCount = 0; //keeps count of same corresponding pairs btw two lists
        for(int i = 0; i < size ; i++){
            sameCount += (list1.get(i).getNeutrons() == list2.get(i).getNeutrons()) ? 1 : 0;//increment sameCount if two pairs neutrons match
        }

        //not all of the corresponding pairs neutrons should be equal for randomness
        assertFalse(sameCount == size);

    }




}
