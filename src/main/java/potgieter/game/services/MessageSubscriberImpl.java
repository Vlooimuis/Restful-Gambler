package potgieter.game.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
@NoArgsConstructor
public class MessageSubscriberImpl implements MessageListener {

    public static List<String> messageList = new ArrayList<>();

    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message recieved: " + Arrays.toString(message.getBody()));
    }
}
