import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    @BeforeEach
    public void init() {
    }

    @Test
    public void createWithNullHorsesList() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
    }

    @Test
    public void createWithNullHorsesListExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void createWithEmptyHorsesList() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
    }

    @Test
    public void createWithEmptyHorsesListExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse: horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horses.get(9), hippodrome.getWinner());
    }
}
