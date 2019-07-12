package potgieter.game.interfaces;

import java.util.Map;

public interface RedisRepository<T> {

    Map<Object, Object> findAll();

    void add(String id, T obj);

    void delete(T id);

    T findPlayer(String id);
}
