package potgieter.game.util;

import java.util.Random;

public class Utils {

    /**
     * Generate number between 0 and 100, simulating random percentage
     * @return Integer between 0 and 100
     */
    public static Integer generateRandomPercentage() {
        return new Random().nextInt(101);
    }

}
