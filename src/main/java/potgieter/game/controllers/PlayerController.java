package potgieter.game.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import potgieter.game.framework.Gambler;
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

    @RequestMapping(value = "/bet", method = RequestMethod.POST)
    public ResponseEntity<PlayerModel> bet(final @RequestParam @Valid String id) {

        // see if player already exists
        PlayerModel playerModel = (PlayerModel) redisPlayerRepository.findPlayer(id);

        if (playerModel == null) {
            playerModel = new PlayerModel();
            redisPlayerRepository.add(id, playerModel);
        }

        new Gambler(playerModel).gamble();

        redisPlayerRepository.add(id, playerModel);

        return new ResponseEntity<>(playerModel, HttpStatus.OK);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(
            @RequestParam @Valid String id) {
        PlayerModel player = new PlayerModel();
        redisPlayerRepository.add(id, player);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisPlayerRepository.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
