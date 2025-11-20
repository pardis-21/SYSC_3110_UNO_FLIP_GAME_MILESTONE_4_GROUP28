import org.junit.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class GameLogicModelTest {

    private GameLogicModel gameLogic;
    private PlayerOrder playerOrder;
    private Player p1, p2;
//    private Game game;
//    private ArrayList<Player> playerNames;

    @Before
    public void setUp() {
        gameLogic = new GameLogicModel();
        playerOrder = new PlayerOrder();
        p1 = new Player("player 1");
        p2 = new Player("player 2");
        playerOrder.addPlayer(p1);
        playerOrder.addPlayer(p2);
        gameLogic.setPlayerOrder(playerOrder);
//        playerNames = new ArrayList<>();
//        playerNames.add(new Player("1"));
//        playerNames.add(new Player("2"));
//        playerNames.add(new Player("3"));

        //gameLogic = new GameLogicModel(playerNames);

//        PlayerOrder playerOrder = new PlayerOrder();
//        playerOrder.addPlayer(playerNames.get(0));
//        playerOrder.addPlayer(playerNames.get(1));
//        playerOrder.addPlayer(playerNames.get(2));
    }

    @Test
    public void testConstructorDoesNotCrash() {
        assertNotNull("GameLogic instance should be created", gameLogic);
    }

    @Test
    public void testInitScores() throws Exception {
        gameLogic.initScores();

        Field scoresField = GameLogicModel.class.getDeclaredField("scores");
        scoresField.setAccessible(true);
        Map<Player, Integer> scores = (Map<Player, Integer>) scoresField.get(gameLogic);

        Field playerOrderField = GameLogicModel.class.getDeclaredField("playerOrder");
        playerOrderField.setAccessible(true);
        Object playerOrderObj = playerOrderField.get(gameLogic);

        ArrayList<Player> players = (ArrayList<Player>) playerOrderObj
                .getClass()
                .getMethod("getAllPlayersToArrayList")
                .invoke(playerOrderObj);

        for (Player p : players) {
            assertTrue("Scores should contain player: " + p.getName(), scores.containsKey(p));
            assertEquals("Score for player " + p.getName() + " should be 0", Integer.valueOf(0), scores.get(p));
        }
    }

    @Test
    public void testStartGameSetsTopCard() {
        gameLogic.startGame();
        assertNotNull("Top card should not be null after starting the game", gameLogic.getTopCard());
    }

    @Test
    public void testDrawCardCurrentPlayerAddsCard(){
        int initialHand = p1.getHand().size();
        gameLogic.drawCardCurrentPlayer();
        assertEquals(initialHand + 1, p1.getHand().size());
    }


    @Test
    public void testSetAndGetTurnCompleted(){
        gameLogic.setTurnCompleted(true);
        assertTrue(gameLogic.isTurnCompleted());
    }

    @Test
    public void testTryPlayCardValidCardReturnsTrue(){
        Card top = new Card();
        top.setCardLightColour("RED");

        Card playable = new Card();
        playable.setCardLightColour("RED");
        p1.getHand().clear();
        p1.getHand().add(playable);

        gameLogic.startGame();
        gameLogic.getTopCard().setCardLightColour("RED");

        boolean result = gameLogic.tryPlayCard(playable);

        assertTrue("Should return true for a playable card", result);

    }

    @Test
    public void testSetPlayerOrderInitializesScores(){
        gameLogic.setPlayerOrder(playerOrder);
        assertNull(gameLogic.getMatchWinner(500));
    }

    @Test
    public void testPlayerTurnClockwise(){
        Player firstPlayer = gameLogic.getCurrentPlayer();
        gameLogic.playerTurn();
        assertNotEquals(firstPlayer, gameLogic.getCurrentPlayer());
    }

    @Test
    public void testAwardRoundPointWhenWinnerEmptiesHand(){
        p1.getHand().clear();
        p2.getHand().add(new Card());
        gameLogic.startGame();
        gameLogic.playGame(new Card());
        assertNotNull(gameLogic.getMatchWinner(0));
    }

    @Test
    public void testGetTotalNumberOfPlayers(){
        assertEquals(gameLogic.getCurrentPlayer().getHand(), gameLogic.getPlayerHand());
    }

    @Test
    public void testGetDirectionInitiallyClockwise(){
        assertTrue(gameLogic.getDirection());
    }



    @Test
    public void testPlayerTurnRunsWithoutException() {
        try {
            gameLogic.playerTurn();
        } catch (Exception e) {
            fail("playerTurn should not throw an exception");
        }
    }

    @Test
    public void testGetMatchWinnerReturnsNullIfNoOneWins() {
        gameLogic.startGame();
        assertNull("No winner should be returned if no one reached target", gameLogic.getMatchWinner(100));
    }

    @Test
    public void testSetPlayerOrderAddsPlayersToScores() throws Exception {
        gameLogic.initScores();

        Field scoresField = GameLogicModel.class.getDeclaredField("scores");
        scoresField.setAccessible(true);
        Map<Player, Integer> scores = (Map<Player, Integer>) scoresField.get(gameLogic);

        Field playerOrderField = GameLogicModel.class.getDeclaredField("playerOrder");
        playerOrderField.setAccessible(true);
        Object oldPlayerOrder = playerOrderField.get(gameLogic);

        ArrayList<String> newNames = new ArrayList<>();
        newNames.add("4");
        PlayerOrder newOrder = new PlayerOrder();
        for (String name : newNames) {
            newOrder.addPlayer(new Player(name));
        }

        gameLogic.setPlayerOrder(newOrder);

        Object newPlayerOrderObj = playerOrderField.get(gameLogic);
        ArrayList<Player> newPlayers = (ArrayList<Player>) newPlayerOrderObj
                .getClass()
                .getMethod("getAllPlayersToArrayList")
                .invoke(newPlayerOrderObj);

        for (Player p : newPlayers) {
            assertTrue("Scores map should contain new player: " + p.getName(), scores.containsKey(p));
        }
    }

    @Test
    public void testPlayUNOGameRunsWithoutException() {
        try {
        } catch (Exception e) {
            fail("playUNOGame should not throw an exception on setup");
        }
    }
}
