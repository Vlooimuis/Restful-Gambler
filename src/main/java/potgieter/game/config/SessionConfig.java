package potgieter.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import potgieter.game.interfaces.MessagePublisher;
import potgieter.game.interfaces.RedisRepository;
import potgieter.game.repository.RedisPlayerRepositoryImpl;
import potgieter.game.services.MessagePublisherImpl;
import potgieter.game.services.MessageSubscriberImpl;

@Configuration
@EnableRedisHttpSession
public class SessionConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(), topic());
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new MessageSubscriberImpl());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("RestGame");
    }

    @Bean
    RedisPlayerRepositoryImpl redisRepository() {
        return new RedisPlayerRepositoryImpl(redisTemplate());
    }
}
