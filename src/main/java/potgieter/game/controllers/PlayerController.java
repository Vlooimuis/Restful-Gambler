package potgieter.game.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import potgieter.game.framework.Gambler;
import potgieter.game.models.GambleResult;
import potgieter.game.models.PlayerModel;
import potgieter.game.repository.RedisPlayerRepositoryImpl;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class PlayerController {

    @Autowired
    RedisPlayerRepositoryImpl redisPlayerRepository;

    @RequestMapping(value = "/bet/{id}", method = RequestMethod.POST)
    public ResponseEntity<PlayerModel> bet(final @PathVariable @Valid String id) {

        PlayerModel playerModel = (PlayerModel) redisPlayerRepository.findPlayer(id);

        if (playerModel == null) {
            playerModel = new PlayerModel();
            redisPlayerRepository.add(id, playerModel);
        }

        new Gambler(playerModel).gamble();

        redisPlayerRepository.add(id, playerModel);

        return new ResponseEntity<>(playerModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/player/{id}/round/{roundNumber}", method = RequestMethod.GET)
    public ResponseEntity<GambleResult> findPlayerRound(
            @PathVariable @Valid String id, @PathVariable @Valid String roundNumber) {
        GambleResult result = redisPlayerRepository.findPlayerRound(id, roundNumber);

        if (result == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<PlayerModel> find(
            @PathVariable @Valid String id) {
        PlayerModel player = (PlayerModel) redisPlayerRepository.findPlayer(id);

        if (player == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(player, HttpStatus.OK);
    }


    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> add(
            @PathVariable @Valid String id) {
        PlayerModel player = new PlayerModel();
        redisPlayerRepository.add(id, player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/values")
    public @ResponseBody
    Map<String, String> findAll() {
        Map<Object, Object> aa = redisPlayerRepository.findAll();
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Object, Object> entry : aa.entrySet()) {
            if (entry.getValue() != null) {
                String key = (String) entry.getKey();
                map.put(key, aa.get(key).toString());
            }
        }
        return map;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@PathVariable String id) {
        redisPlayerRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
