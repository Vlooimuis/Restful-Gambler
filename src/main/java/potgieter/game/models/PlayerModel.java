package potgieter.game.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RedisHash("Player")
public class PlayerModel implements Serializable {
    private RoundModeEnum roundMode = RoundModeEnum.NORMAL;
    private Integer coins = 1000000;
    private List<GambleResult> gambleResult = new ArrayList<>();
}
