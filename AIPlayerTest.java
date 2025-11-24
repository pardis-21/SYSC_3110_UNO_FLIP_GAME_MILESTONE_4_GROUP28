import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.*;


public class AIPlayerTest {

    private GameLogicModel gameLogicModel;
    private PlayerOrder playerOrder;
    private AIPlayer aiPlayer;
    private Player human;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        gameLogicModel = new GameLogicModel();
        playerOrder = new PlayerOrder();
        aiPlayer = new AIPlayer("CHATGPT");
        human = new Player("BOB");

        playerOrder.addPlayer(aiPlayer);
        playerOrder.addPlayer(human);
        gameLogicModel.setPlayerOrder(playerOrder);
        gameLogicModel.initScores();

        Card topCard = new Card();
        topCard.setCardLightColour("RED");

    }

    @org.junit.jupiter.api.Test
    void chooseCardToPlay() {
    }

    @org.junit.jupiter.api.Test
    void chooseBestLightColour() {
    }

    @org.junit.jupiter.api.Test
    void chooseBestDarkColour() {
    }
}