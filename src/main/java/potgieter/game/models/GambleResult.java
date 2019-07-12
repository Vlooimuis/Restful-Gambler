package potgieter.game.models;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@RedisHash("GambleResult")
public class GambleResult implements Serializable {
    private long roundNumber = 1;
    private List<String> messageList;

    public GambleResult() {
        messageList = new ArrayList<>();
    }

    public void addMessage(String message) {
        if (messageList != null) {
            messageList.add(message);
        }
    }
}
