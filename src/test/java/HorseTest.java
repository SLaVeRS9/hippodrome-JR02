import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HorseTest {
    Horse normalHorse;
    static double normalDistance;
    static double normalSpeed;
    static String defaultHorseName;

    @BeforeEach
    public void init() {
        normalDistance = 10.0;
        normalSpeed = 10.0;
        defaultHorseName = "horse";
        normalHorse = new Horse(defaultHorseName, normalSpeed, normalDistance);
    }

    @Test
    public void createWithNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0, 0)
        );
    }

    @Test
    public void createWithNulNameThrowCorrectMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0, 0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t"})
    public void createWithBlankName(String string) {
        assertThrows(IllegalArgumentException.class,
                () -> {new Horse(string, 0, 0);}
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t"})
    public void createWithBlankNameThrowCorrectMessage(String string) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(string, 0, 0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void createWithNegativeSpeed() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(defaultHorseName, -1.0, 0)
        );
    }

    @Test
    public void createWithNegativeSpeedThrowCorrectMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {new Horse(defaultHorseName, -1.0, 0);});
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void createWithNegativeDistance() {
        assertThrows(IllegalArgumentException.class,
                () -> {new Horse(defaultHorseName, 0, -1.0);}
        );
    }

    @Test
    public void createWithNegativeDistanceThrowCorrectMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(defaultHorseName, 0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() {
        Horse horse = new Horse(defaultHorseName, 1, 1);
        assertEquals(defaultHorseName, horse.getName());
    }

    @Test
    public void getSpeed() {
        double horseSpeed = 10.0;
        Horse horse = new Horse(defaultHorseName, horseSpeed, 1);
        assertEquals(horseSpeed, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = normalHorse;
        assertEquals(normalDistance, horse.getSpeed());
    }

    @Test
    public void moveVerifyGetRandomDouble() {
        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)){
            normalHorse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    public void moveVerifyGetRandomDoubleFormula(double d) {
        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)){
            Horse horse = normalHorse;
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(d);

            double expectedResult = normalHorse.getDistance() + normalHorse.getSpeed() * d;
            horse.move();

            assertEquals(expectedResult, horse.getDistance());
        }
    }
}
