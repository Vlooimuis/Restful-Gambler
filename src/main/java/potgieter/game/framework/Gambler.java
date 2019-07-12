package potgieter.game.framework;

import potgieter.game.models.GambleResult;
import potgieter.game.models.PlayerModel;
import potgieter.game.models.RoundModeEnum;
import potgieter.game.util.Utils;

import java.util.ArrayList;

public class Gambler {

    private static final int BETTING_COST = 10;
    private static final int WIN_AMOUNT = 20;
    private static final int WINNING_PERCENTAGE = 30;
    private static final int FREE_PERCENTAGE = 10;

    private PlayerModel player;

    public Gambler(PlayerModel player) {
        this.player = player;
    }

    public void gamble() {
        if (player == null) {
            System.out.println("No player to gamble!");
        } else {
            roll();
        }
    }

    private void roll() {
        System.out.println("Player currently in mode: " + player.getRoundMode());

        GambleResult result = new GambleResult();
        result.setRoundNumber(player.getGambleResult().size() + 1);

        if (player.getRoundMode() == RoundModeEnum.NORMAL) {
            // infinite coins!
            if (!hasCoins()) {
                player.setCoins(1000000);
            }

            deductCoins();
            result.addMessage(BETTING_COST + " coins have been deducted");
        }


        if (isWin()) {
            result.addMessage("You win " + WIN_AMOUNT + " coins!");
            player.setCoins(player.getCoins() + WIN_AMOUNT);
        } else {
            result.addMessage("You win 0 coins");
        }

        if (isFree()) {
            result.addMessage("Lucky! Next round is free!");
            player.setRoundMode(RoundModeEnum.FREE);
        } else {
            player.setRoundMode(RoundModeEnum.NORMAL);
        }


        player.getGambleResult().add(result);
    }

    private boolean isFree() {
        return Utils.generateRandomPercentage() < FREE_PERCENTAGE;
    }

    private boolean isWin() {
        return Utils.generateRandomPercentage() < WINNING_PERCENTAGE;
    }

    private boolean hasCoins() {
        return player.getCoins() >= BETTING_COST;
    }

    private void deductCoins() {
        player.setCoins(player.getCoins() - BETTING_COST);

    }

}
