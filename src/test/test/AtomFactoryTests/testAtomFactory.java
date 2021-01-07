package AtomFactoryTests;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;


public class testAtomFactory {
    @Test
    public void name() {
        assertEquals(5 + 2, 7);
        assumeFalse(5 < 1);

    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @AfterClass
    public static void afterClass() throws Exception {

    }
}
