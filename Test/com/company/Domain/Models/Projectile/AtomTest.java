package com.company.Domain.Models.Projectile;

import com.company.Domain.Models.GameFactory;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.ShieldType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class AtomTest {
    // KORAY TECIMER tests
    private int L;

    @Before
    public void setup(){
        GameFactory gameFactory = GameFactory.getInstance();
        gameFactory.setL(4);
        L = gameFactory.getL();

    }

    @Test
    public void initializedAtomShieldMapNullTest() {
        Atom atom1 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        assertNull(atom1.getShieldMap());
        atom1.addShield(ShieldType.THETA);
        assertNotNull(atom1.getShieldMap());
    }

    @Test
    public void firstTimeAddingShieldTest() {
        Atom atom = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));

        HashMap<ShieldType,Integer> shieldMap = new HashMap();
        shieldMap.put(ShieldType.ETA,1);
        shieldMap.put(ShieldType.LOTA,0);
        shieldMap.put(ShieldType.THETA,0);
        shieldMap.put(ShieldType.ZETA,0);

        atom.addShield(ShieldType.ETA);
        assertEquals(atom.getShieldMap(), shieldMap);
    }

    @Test
    public void shieldAmountTest() {
        Atom atom = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        HashMap<ShieldType,Integer> shieldMap = new HashMap();

        Integer[] ShieldMapNumbers = {1,4,3,2};
        ShieldType[] shieldTypes = {ShieldType.ETA, ShieldType.LOTA, ShieldType.THETA, ShieldType.ZETA};

        shieldMap.put(ShieldType.ETA,1);
        shieldMap.put(ShieldType.LOTA,4);
        shieldMap.put(ShieldType.THETA,3);
        shieldMap.put(ShieldType.ZETA,2);

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < ShieldMapNumbers[i]; j++) {
                atom.addShield(shieldTypes[i]);
            }
        }

        assertEquals(atom.getShieldMap(), shieldMap);
    }

    @Test
    public void reduceSpeedEffectTest() {
        Atom atom1 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        Atom atom2 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        Atom atom3 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        Atom atom4 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));

        atom1.addShield(ShieldType.ETA);
        assertTrue(atom1.getSpeedMultiplier()==0.95);

        atom2.addShield(ShieldType.LOTA);
        assertTrue(atom2.getSpeedMultiplier()==0.93);

        atom3.addShield(ShieldType.THETA);
        assertTrue(atom3.getSpeedMultiplier()==0.91);

        atom4.addShield(ShieldType.ZETA);
        assertTrue(atom4.getSpeedMultiplier()==0.89);

    }

    @Test
    public void combineSpeedEffectTest() {
        Atom atom1 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        Atom atom2 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        Atom atom3 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        Atom atom4 = new Atom(new Coordinate(0,0), new Velocity(0,0), AtomType.ALPHA, true,(int)(L/10),(int)(L/10));
        HashMap<ShieldType,Integer> shieldMap = new HashMap();

        atom1.addShield(ShieldType.LOTA);
        atom1.addShield(ShieldType.ETA);
        atom1.addShield(ShieldType.ETA);

        atom2.addShield(ShieldType.ETA);
        atom2.addShield(ShieldType.LOTA);
        atom2.addShield(ShieldType.ETA);

        assertTrue(atom1.getSpeedMultiplier()==atom2.getSpeedMultiplier());

        atom3.addShield(ShieldType.THETA);
        atom3.addShield(ShieldType.ETA);
        atom3.addShield(ShieldType.LOTA);
        assertTrue(atom3.getSpeedMultiplier()==0.95*0.93*0.91);

        atom4.addShield(ShieldType.ZETA);
        atom4.addShield(ShieldType.ETA);
        atom4.addShield(ShieldType.LOTA);
        assertTrue(atom4.getSpeedMultiplier()==0.95*0.89*0.93);

    }

}
