package potgieter.game.framework;

import potgieter.game.models.GambleResult;
import potgieter.game.models.PlayerModel;
import potgieter.game.models.RoundModeEnum;

import static potgieter.game.framework.Constants.BETTING_COST;
import static potgieter.game.framework.Constants.WIN_AMOUNT;
import static potgieter.game.framework.PlayerDecisions.*;

public class Gambler {

    private PlayerModel player;

    public Gambler(PlayerModel player) {
        this.player = player;
    }

    public void gamble() {
        if (player != null) {
            roll();
        }
    }

    private void roll() {
        GambleResult result = new GambleResult();
        result.setRoundNumber(player.getGambleResult().size() + 1);

        if (player.getRoundMode() == RoundModeEnum.NORMAL) {
            // infinite coins!
            if (!hasCoins(player)) {
                player.setCoins(1000000);
            }

            deductCoins(player);
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


}
