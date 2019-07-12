package potgieter.game.framework;

import potgieter.game.models.GambleResult;
import potgieter.game.models.PlayerModel;
import potgieter.game.models.RoundModeEnum;
import potgieter.game.util.Utils;

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

        GambleResult result = player.getGambleResult();

        if (player.getRoundMode() == RoundModeEnum.NORMAL) {
            if (player.getCoins() >= BETTING_COST) {
                deductCoins();
                result.addMessage(BETTING_COST + " coins have been deducted");
            } else {
                result.addMessage("You have no coins left, which is impressive!");
                return;
            }
        }

        int randomWin = Utils.generateRandomPercentage();
        System.out.println("Random Win: " + randomWin);
        if (randomWin < WINNING_PERCENTAGE) {
            result.addMessage("You win " + WIN_AMOUNT + " coins!");
            player.setCoins(player.getCoins() + WIN_AMOUNT);
        }

        int randomFree = Utils.generateRandomPercentage();
        System.out.println("Random Free: " + randomFree);
        if (randomFree < FREE_PERCENTAGE) {
            result.addMessage("Lucky! Next round is free!");
            player.setRoundMode(RoundModeEnum.FREE);
        } else {
            player.setRoundMode(RoundModeEnum.NORMAL);
        }
    }

    private void deductCoins() {
        player.setCoins(player.getCoins() - BETTING_COST);

    }

}
