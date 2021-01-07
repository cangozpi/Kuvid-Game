package AtomFactoryTests;


import com.company.Domain.Models.GameFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;


public class testAtomFactory {
    //note: if tests do not work when you run the program then you have to do File->ProjectStructure->Modules
    // and then change junit.api and j4test to compile to fix the issue. and use java 1.8
    @Test
    public void name() {
        assertEquals(GameFactory.class, GameFactory.getInstance().getClass());
        assumeFalse(5 < 1);

    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @AfterClass
    public static void afterClass() throws Exception {

    }
}
