package BlenderBreakTests;


import com.company.Domain.Models.Blender;

import com.company.Domain.Models.Inventory;

import com.company.Enums.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;



public class testBreak {

    Inventory inventory;
    Blender blender;

    @Before
    public void init() {
        blender = Blender.getInstance();
        inventory= Inventory.getInstance();

    }


    @Test
    public void removesCorrectAmount(){

        AtomType[] types = AtomType.values();
        for (AtomType atomType: types){
            HashMap<AtomType, Integer>atomAmount = new HashMap<>();
            atomAmount.put(AtomType.ALPHA,100);
            atomAmount.put(AtomType.BETA,100);
            atomAmount.put(AtomType.GAMMA,100);
            atomAmount.put(AtomType.SIGMA,100);
            inventory.setAtomMap(atomAmount);
            switch (atomType) {
                case BETA:
                    blender.breakAtoms(AtomType.BETA, AtomType.ALPHA, 10);
                    assertEquals(inventory.getAtomAmount(AtomType.BETA), 90);
                    break;
                case GAMMA:
                    blender.breakAtoms(AtomType.GAMMA, AtomType.ALPHA, 10);
                    assertEquals(inventory.getAtomAmount(AtomType.GAMMA), 90);
                    break;
                case SIGMA:
                    blender.breakAtoms(AtomType.SIGMA, AtomType.ALPHA, 10);
                    assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 90);
                    break;
            }
        }


    }

    @Test
    public void removesCorrectType(){
        AtomType[] types = AtomType.values();

        for (AtomType atomType: types){
            HashMap<AtomType, Integer>atomAmount = new HashMap<>();
            atomAmount.put(AtomType.ALPHA,100);
            atomAmount.put(AtomType.BETA,100);
            atomAmount.put(AtomType.GAMMA,100);
            atomAmount.put(AtomType.SIGMA,100);
            inventory.setAtomMap(atomAmount);
            switch (atomType) {
                case ALPHA:
                    assertFalse(blender.breakAtoms(AtomType.ALPHA, AtomType.BETA, 10));
                    break;
                case BETA:
                    blender.breakAtoms(AtomType.BETA, AtomType.ALPHA, 10);
                    assertEquals(inventory.getAtomAmount(AtomType.BETA), 90);
                    assertEquals(inventory.getAtomAmount(AtomType.GAMMA), 100);
                    assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 100);
                    break;
                case GAMMA:
                    blender.breakAtoms(AtomType.GAMMA, AtomType.ALPHA, 10);
                    assertEquals(inventory.getAtomAmount(AtomType.GAMMA), 90);
                    assertEquals(inventory.getAtomAmount(AtomType.BETA), 100);
                    assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 100);
                    break;
                case SIGMA:
                    blender.breakAtoms(AtomType.SIGMA, AtomType.ALPHA, 10);
                    assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 90);
                    assertEquals(inventory.getAtomAmount(AtomType.BETA), 100);
                    assertEquals(inventory.getAtomAmount(AtomType.GAMMA), 100);
                    break;
            }
        }
    }

    @Test
    public void generatesCorrectAmount(){
        AtomType[] types = AtomType.values();

        for (AtomType source: types){
            switch (source){
                case SIGMA:
                    for (AtomType product: types){
                        HashMap<AtomType, Integer>atomAmount = new HashMap<>();
                        atomAmount.put(AtomType.ALPHA,100);
                        atomAmount.put(AtomType.BETA,100);
                        atomAmount.put(AtomType.GAMMA,100);
                        atomAmount.put(AtomType.SIGMA,100);
                        inventory.setAtomMap(atomAmount);
                        switch (product) {
                            case ALPHA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 140);
                                break;
                            case BETA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 130);
                                break;
                            case GAMMA:
                                blender.breakAtoms(source,product, 10);
                                assertEquals(inventory.getAtomAmount(product), 120);
                                break;
                        }
                    }
                break;
                case GAMMA:
                    for (AtomType product: types){
                        HashMap<AtomType, Integer>atomAmount = new HashMap<>();
                        atomAmount.put(AtomType.ALPHA,100);
                        atomAmount.put(AtomType.BETA,100);
                        atomAmount.put(AtomType.GAMMA,100);
                        atomAmount.put(AtomType.SIGMA,100);
                        inventory.setAtomMap(atomAmount);
                        switch (product) {
                            case ALPHA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 130);
                                break;
                            case BETA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 120);
                                break;
                        }
                    }
                break;
                case BETA:
                    HashMap<AtomType, Integer>atomAmount = new HashMap<>();
                    atomAmount.put(AtomType.ALPHA,100);
                    atomAmount.put(AtomType.BETA,100);
                    atomAmount.put(AtomType.GAMMA,100);
                    atomAmount.put(AtomType.SIGMA,100);
                    inventory.setAtomMap(atomAmount);
                    for (AtomType product: types) {
                        if (product == AtomType.ALPHA) {
                            blender.breakAtoms(source, product, 10);
                            assertEquals(inventory.getAtomAmount(product), 120);
                        }
                    }
            }
        }

    }

    @Test
    public void generatesCorrectType(){
        AtomType[] types = AtomType.values();

        for (AtomType source: types){
            switch (source){
                case SIGMA:
                    for (AtomType product: types){
                        HashMap<AtomType, Integer>atomAmount = new HashMap<>();
                        atomAmount.put(AtomType.ALPHA,100);
                        atomAmount.put(AtomType.BETA,100);
                        atomAmount.put(AtomType.GAMMA,100);
                        atomAmount.put(AtomType.SIGMA,100);
                        inventory.setAtomMap(atomAmount);
                        switch (product) {
                            case ALPHA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 140);
                                assertEquals(inventory.getAtomAmount(AtomType.BETA), 100);
                                assertEquals(inventory.getAtomAmount(AtomType.GAMMA), 100);
                                break;
                            case BETA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 130);
                                assertEquals(inventory.getAtomAmount(AtomType.ALPHA), 100);
                                assertEquals(inventory.getAtomAmount(AtomType.GAMMA), 100);
                                break;
                            case GAMMA:
                                blender.breakAtoms(source,product, 10);
                                assertEquals(inventory.getAtomAmount(product), 120);
                                assertEquals(inventory.getAtomAmount(AtomType.ALPHA), 100);
                                assertEquals(inventory.getAtomAmount(AtomType.BETA), 100);
                                break;
                        }
                    }
                    break;
                case GAMMA:
                    for (AtomType product: types){
                        HashMap<AtomType, Integer>atomAmount = new HashMap<>();
                        atomAmount.put(AtomType.ALPHA,100);
                        atomAmount.put(AtomType.BETA,100);
                        atomAmount.put(AtomType.GAMMA,100);
                        atomAmount.put(AtomType.SIGMA,100);
                        inventory.setAtomMap(atomAmount);
                        switch (product) {
                            case ALPHA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 130);
                                assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 100);
                                assertEquals(inventory.getAtomAmount(AtomType.BETA), 100);
                                break;
                            case BETA:
                                blender.breakAtoms(source, product, 10);
                                assertEquals(inventory.getAtomAmount(product), 120);
                                assertEquals(inventory.getAtomAmount(AtomType.ALPHA), 100);
                                assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 100);
                                break;
                        }
                    }
                    break;
                case BETA:
                    HashMap<AtomType, Integer>atomAmount = new HashMap<>();
                    atomAmount.put(AtomType.ALPHA,100);
                    atomAmount.put(AtomType.BETA,100);
                    atomAmount.put(AtomType.GAMMA,100);
                    atomAmount.put(AtomType.SIGMA,100);
                    inventory.setAtomMap(atomAmount);
                    for (AtomType product: types) {
                        if (product == AtomType.ALPHA) {
                            blender.breakAtoms(source, product, 10);
                            assertEquals(inventory.getAtomAmount(product), 120);
                            assertEquals(inventory.getAtomAmount(AtomType.GAMMA), 100);
                            assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 100);
                        }
                    }
            }
        }
    }

    @Test
    public void checkOrder(){
        AtomType[] types = AtomType.values();
        HashMap<AtomType, Integer>atomAmount = new HashMap<>();
        atomAmount.put(AtomType.ALPHA,100);
        atomAmount.put(AtomType.BETA,100);
        atomAmount.put(AtomType.GAMMA,100);
        atomAmount.put(AtomType.SIGMA,100);
        inventory.setAtomMap(atomAmount);
        for (AtomType source: types){
            switch (source){

                case ALPHA:
                    assertFalse(blender.breakAtoms(AtomType.ALPHA, AtomType.BETA, 10));
                    break;
                case BETA:
                    assertTrue(blender.breakAtoms(AtomType.BETA, AtomType.ALPHA, 10));
                    assertFalse(blender.breakAtoms(AtomType.BETA, AtomType.GAMMA, 10));
                    assertFalse(blender.breakAtoms(AtomType.BETA, AtomType.SIGMA, 10));
                    break;
                case GAMMA:
                    assertFalse(blender.breakAtoms(AtomType.GAMMA, AtomType.SIGMA, 10));
                    assertTrue(blender.breakAtoms(AtomType.GAMMA, AtomType.BETA, 10));
                    assertTrue(blender.breakAtoms(AtomType.GAMMA, AtomType.ALPHA, 10));
                    break;
                case SIGMA:
                    assertTrue(blender.breakAtoms(AtomType.SIGMA, AtomType.ALPHA, 10));
                    assertTrue(blender.breakAtoms(AtomType.SIGMA, AtomType.BETA, 10));
                    assertTrue(blender.breakAtoms(AtomType.SIGMA, AtomType.GAMMA, 10));
                    break;
            }
        }

    }
    @Test
    public void breakToItself(){
        AtomType[] types = AtomType.values();
        HashMap<AtomType, Integer>atomAmount = new HashMap<>();
        atomAmount.put(AtomType.ALPHA,100);
        atomAmount.put(AtomType.BETA,100);
        atomAmount.put(AtomType.GAMMA,100);
        atomAmount.put(AtomType.SIGMA,100);
        inventory.setAtomMap(atomAmount);
        for (AtomType source: types){
            switch (source){
                case ALPHA:
                    assertFalse(blender.breakAtoms(AtomType.ALPHA, AtomType.ALPHA, 10));
                    break;
                case BETA:
                    assertFalse(blender.breakAtoms(AtomType.BETA, AtomType.BETA, 10));
                    break;
                case GAMMA:
                    assertFalse(blender.breakAtoms(AtomType.GAMMA, AtomType.GAMMA, 10));

                    break;
                case SIGMA:
                    assertFalse(blender.breakAtoms(AtomType.SIGMA, AtomType.SIGMA, 10));
                    break;
            }
        }
    }

    @After
    public void tearDown() throws Exception {


    }







}
