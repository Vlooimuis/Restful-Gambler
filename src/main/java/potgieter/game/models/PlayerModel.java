package potgieter.game.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PlayerModel implements Serializable {

    private RoundModeEnum roundMode = RoundModeEnum.NORMAL;
    private Integer coins = 1000000;
    private GambleResult gambleResult = new GambleResult();

}
