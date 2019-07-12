package potgieter.game.framework;

import potgieter.game.models.PlayerModel;
import potgieter.game.util.Utils;

import static potgieter.game.framework.Constants.*;

class PlayerDecisions {

    static boolean isFree() {
        return Utils.generateRandomPercentage() < FREE_PERCENTAGE;
    }

    static boolean isWin() {
        return Utils.generateRandomPercentage() < WINNING_PERCENTAGE;
    }

    static boolean hasCoins(PlayerModel player) {
        return player.getCoins() >= BETTING_COST;
    }

    static void deductCoins(PlayerModel player) {
        player.setCoins(player.getCoins() - BETTING_COST);

    }
}
