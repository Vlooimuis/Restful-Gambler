package potgieter.game.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import potgieter.game.interfaces.MessagePublisher;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePublisherImpl implements MessagePublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }


}
