package test;


import com.company.Domain.Models.*;
import com.company.Domain.Models.Projectile.Atom;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.ReactionBlocker;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.*;

import org.junit.*;
import static org.junit.Assert.*;



public class sameTypeTest{

    // BATU HELVACIOĞLU TESTS

    private MoleculeFactory moleculeFactory;
    private AtomFactory atomFactory;
    private Coordinate pos;
    private Velocity v;
    private int h;
    private int w;
    private Molecule alphaMolecule;
    private Molecule alphaLMolecule;
    private Molecule betaMolecule;
    private Molecule betaLMolecule;
    private Molecule gammaMolecule;
    private Molecule sigmaMolecule;

    private ReactionBlocker alphaBlocker;
    private ReactionBlocker betaBlocker;
    private ReactionBlocker sigmaBlocker;
    private ReactionBlocker gammaBlocker;
    private Atom alphaAtom;
    private Atom betaAtom;
    private Atom sigmaAtom;
    private Atom gammaAtom;


    private PowerUp alphaPowerUp;
    private PowerUp betaPowerUp;
    private PowerUp sigmaPowerUp;
    private PowerUp gammaPowerUp;
    private GameFactory game;

    @Before
    public void init() {
        this.game = GameFactory.getInstance();
        this.moleculeFactory = new MoleculeFactory();
        this.atomFactory = new AtomFactory();
        this.pos = new Coordinate(0,0);
        this.v = new Velocity(0, 0);
        this.h = 10;
        this.w = 10;
        this.alphaMolecule = moleculeFactory.getInstance(pos, v, false, MoleculeType.ALPHA , h, w);
        this.alphaLMolecule = moleculeFactory.getInstance(pos, v, false, MoleculeType.ALPHA_L , h, w);
        this.betaMolecule = moleculeFactory.getInstance(pos, v, false, MoleculeType.BETA , h, w);
        this.betaLMolecule = moleculeFactory.getInstance(pos, v, false, MoleculeType.BETA_L , h, w);
        this.gammaMolecule = moleculeFactory.getInstance(pos, v, false, MoleculeType.GAMMA , h, w);
        this.sigmaMolecule = moleculeFactory.getInstance(pos, v, false, MoleculeType.SIGMA , h, w);

        this.alphaBlocker = new ReactionBlocker(pos, v, ReactionBlockerType.ALPHA_B,false,  h, w);
        this.betaBlocker = new ReactionBlocker(pos, v, ReactionBlockerType.BETA_B, false, h, w);
        this.sigmaBlocker = new ReactionBlocker(pos, v, ReactionBlockerType.SIGMA_B, false, h, w);
        this.gammaBlocker = new ReactionBlocker(pos, v, ReactionBlockerType.GAMMA_B,false,  h, w);

        this.alphaPowerUp = new PowerUp(pos, v, PowerUpType.ALPHA,false,  h, w);
        this.betaPowerUp = new PowerUp(pos, v, PowerUpType.BETA, false, h, w);
        this.sigmaPowerUp = new PowerUp(pos, v, PowerUpType.SIGMA,false,  h, w);
        this.gammaPowerUp = new PowerUp(pos, v, PowerUpType.GAMMA,false,  h, w);

        this.alphaAtom = atomFactory.getInstance(pos, v,  AtomType.ALPHA ,false, h, w);
        this.betaAtom = atomFactory.getInstance(pos, v,  AtomType.BETA ,false, h, w);
        this.sigmaAtom = atomFactory.getInstance(pos, v,  AtomType.SIGMA ,false, h, w);
        this.gammaAtom = atomFactory.getInstance(pos, v,  AtomType.GAMMA ,false, h, w);


    }


    @Test
    public void blockerMoleculeTest(){
        assertTrue(GameFactory.getInstance().isSameType(alphaBlocker,alphaMolecule));
        assertTrue(GameFactory.getInstance().isSameType(alphaBlocker,alphaLMolecule));
        assertTrue(GameFactory.getInstance().isSameType(betaBlocker,betaMolecule));
        assertTrue(GameFactory.getInstance().isSameType(betaBlocker,betaLMolecule));
        assertTrue(GameFactory.getInstance().isSameType(sigmaBlocker,sigmaMolecule));
        assertTrue(GameFactory.getInstance().isSameType(gammaBlocker,gammaMolecule));


    }


    @Test
    public void atomMoleculeTest(){
        assertTrue(GameFactory.getInstance().isSameType(alphaAtom,alphaMolecule));
        assertTrue(GameFactory.getInstance().isSameType(alphaAtom,alphaLMolecule));
        assertTrue(GameFactory.getInstance().isSameType(betaAtom,betaMolecule));
        assertTrue(GameFactory.getInstance().isSameType(betaAtom,betaLMolecule));
        assertTrue(GameFactory.getInstance().isSameType(sigmaAtom,sigmaMolecule));
        assertTrue(GameFactory.getInstance().isSameType(gammaAtom,gammaMolecule));


    }

    @Test
    public void blockerPowerUpTest(){
        assertTrue(GameFactory.getInstance().isSameType(alphaBlocker,alphaPowerUp));
        assertTrue(GameFactory.getInstance().isSameType(betaBlocker,betaPowerUp));
        assertTrue(GameFactory.getInstance().isSameType(sigmaBlocker,sigmaPowerUp));
        assertTrue(GameFactory.getInstance().isSameType(gammaBlocker,gammaPowerUp));


    }

    public void blockerAtomTest(){
        assertTrue(GameFactory.getInstance().isSameType(alphaBlocker,alphaAtom));
        assertTrue(GameFactory.getInstance().isSameType(betaBlocker,betaAtom));
        assertTrue(GameFactory.getInstance().isSameType(sigmaBlocker,sigmaAtom));
        assertTrue(GameFactory.getInstance().isSameType(gammaBlocker,gammaAtom));
        assertFalse(GameFactory.getInstance().isSameType(alphaBlocker,betaAtom));

    }





















}