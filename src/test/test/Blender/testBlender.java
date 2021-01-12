package test.Blender;


import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.Inventory;
import com.company.Domain.Models.Blender;
import com.company.Enums.AtomType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;

import static org.junit.Assert.*;



public class testBlender {
    //ÖYKÜM SAKIZ TESTS
    private int A = 0;
    Blender blender;
    Inventory inventory;
    GameFactory Gf;
    HashMap<AtomType, Integer> amount = new HashMap<>();

    @Before
    public void init(){
        amount.put(AtomType.ALPHA,10);
        amount.put(AtomType.BETA,10);
        amount.put(AtomType.SIGMA,10);
        amount.put(AtomType.GAMMA,10);
        Gf = GameFactory.getInstance();
       inventory = Inventory.getInstance();
        blender = Blender.getInstance();
        inventory.setAtomMap(amount);
    }
    @Test
    public void blendlowertohigher(){

        assertTrue(blender.blendAtoms(AtomType.ALPHA, AtomType.BETA,3)); }
    @Test
    public void blendhighertolower() {
        assertFalse(blender.blendAtoms(AtomType.GAMMA, AtomType.BETA,2)); }

        @Test // It returns true because we did not check in the method
    public void SameType(){
        assertTrue(blender.blendAtoms(AtomType.SIGMA, AtomType.SIGMA,6));}

        @Test // It should not change the amount because same type
    public void SameTypeAmountCheck(){
        amount.put(AtomType.SIGMA,18);
        inventory.setAtomMap(amount);
            blender.blendAtoms(AtomType.SIGMA, AtomType.SIGMA,2);
        assertEquals(inventory.getAtomAmount(AtomType.SIGMA), 18);}

    @Test
    public void sourceAmountCheckLESS(){
        A = inventory.getAtomAmount(AtomType.ALPHA);
        blender.blendAtoms(AtomType.ALPHA, AtomType.GAMMA,2);
        assertTrue(blender.blendAtoms(AtomType.BETA, AtomType.SIGMA,2));}

    @Test
    public void sourceAmountCheckMORE(){
        A = inventory.getAtomAmount(AtomType.BETA);
        assertFalse(blender.blendAtoms(AtomType.BETA, AtomType.SIGMA,20));}

    @Test
    public void sourceAmountCheckSAME(){
        A = inventory.getAtomAmount(AtomType.GAMMA);
        assertTrue(blender.blendAtoms(AtomType.GAMMA, AtomType.SIGMA,10));}

    @Test
    public void GammaSigmaAmount(){
        A = inventory.getAtomAmount(AtomType.SIGMA);
        blender.blendAtoms(AtomType.GAMMA, AtomType.SIGMA,2);
        assertEquals(inventory.getAtomAmount(AtomType.SIGMA), A +1);}

    @Test
    public void AlphaSigmaAmount(){
        A = inventory.getAtomAmount(AtomType.SIGMA);
        blender.blendAtoms(AtomType.ALPHA, AtomType.SIGMA,8);
        assertEquals(inventory.getAtomAmount(AtomType.SIGMA), A +2);}

}
