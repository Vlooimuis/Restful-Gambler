package potgieter.game.framework;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import potgieter.game.models.PlayerModel;
import potgieter.game.models.RoundModeEnum;

import static org.junit.Assert.assertTrue;


public class GamblerTest {

    @Mock
    private PlayerModel model;
    @Mock
    private Gambler gambler;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        model = new PlayerModel();
        gambler = new Gambler(model);
    }

    @Test
    public void shouldDeductCoinsOnNormalRound() {
        model.setCoins(100);
        model.setRoundMode(RoundModeEnum.NORMAL);

        gambler.gamble();

        assertTrue(model.getCoins() == 90 || model.getCoins() == 110); //could have won

    }

    @Test
    public void shouldNotDeductCoinsOnFreeRound() {
        model.setCoins(100);
        model.setRoundMode(RoundModeEnum.FREE);

        gambler.gamble();

        assertTrue(model.getCoins() == 100 || model.getCoins() == 120); //could have won
    }

}