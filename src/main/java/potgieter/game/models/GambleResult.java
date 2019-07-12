package potgieter.game.models;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class GambleResult implements Serializable {
    private List<String> messageList;

    GambleResult() {
        messageList = new ArrayList<>();
    }

    public void addMessage(String message) {
        if (messageList != null) {
            messageList.add(message);
        }
    }
}
