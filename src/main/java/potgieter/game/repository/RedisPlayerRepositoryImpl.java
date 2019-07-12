package potgieter.game.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import potgieter.game.interfaces.RedisRepository;
import potgieter.game.models.GambleResult;
import potgieter.game.models.PlayerModel;

import javax.annotation.PostConstruct;
import java.util.Map;

public class RedisPlayerRepositoryImpl implements RedisRepository {

    private static final String KEY = "Player";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisPlayerRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public Map<Object, Object> findAll() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void add(String id, Object obj) {
        PlayerModel model = new PlayerModel();
        model.setRoundMode(((PlayerModel) obj).getRoundMode());
        model.setCoins(((PlayerModel) obj).getCoins());
        model.setGambleResult(((PlayerModel) obj).getGambleResult());
        System.out.println("Persisting " + model);
        hashOperations.put(KEY, id, model);
    }

    @Override
    public void delete(Object id) {
        hashOperations.delete(KEY, id);
    }

    @Override
    public Object findPlayer(String id) {
        return hashOperations.get(KEY, id);
    }

    public GambleResult findPlayerRound(String id, String roundNumber) {
        PlayerModel model = (PlayerModel) hashOperations.get(KEY, id);
        for (GambleResult gambleResult : model.getGambleResult()) {
            if (gambleResult.getRoundNumber() == Long.parseLong(roundNumber)) {
                return gambleResult;
            }
        }

        return null;
    }
}
