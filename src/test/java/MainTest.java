import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {

    @Disabled
    @Test
    @Timeout(22)
    public void mainTimeout() throws Exception {
        Main.main(null);
    }
}
