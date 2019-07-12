package potgieter.game.util;

import java.util.Random;
import java.util.UUID;

public class Utils {

    public static Integer generateRandomPercentage() {
        return new Random().nextInt(100);
    }

    public static String generateUUID() {

        return String.valueOf(UUID.randomUUID());
    }
}
