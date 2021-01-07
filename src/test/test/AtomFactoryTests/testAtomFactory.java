package AtomFactoryTests;


import com.company.Domain.Models.GameFactory;
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
    @DisplayName("A special test case")
    public void beforeClass() throws Exception {

    }

    //denotes that the annotated method will be executed after each test method
    @AfterEach
    public void afterClass() throws Exception {

    }

    @Test
    @DisplayName("A special test case")
    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    public void name() {
        assertEquals(GameFactory.class, GameFactory.getInstance().getClass());
        assumeFalse(5 < 1);

    }

}
