import java.io.Serializable;
import java.util.*;

/**
 * This class creates a little image for saving info of the game
 * When you close the game, this class will provide the necessary functinos
 * to save the state's information such as the game, the score, the cards in that
 * players hand and whos turn it is.
 * @Author Charis Nobossi 101297742
 * @Author Anvita Ala 101301514
 * @Author Pardis Ehsani 101300400
 * @Author Pulcherie Mbaye 101302394
 */
public class UNOGameStateSnapShot implements Serializable {
    private GameLogicModel gameLogicModel;
    private Card playerCard;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;
    private ArrayList<Player> currentPlayers;
    private Player currentPlayer;
    public final Map<Player, Integer> scores = new HashMap<>();
    private static final int SEVEN = 7;
    private boolean turnCompleted = false;
    private int numPlayers = 0;
    private boolean direction;
    public boolean lightMode = true;
    private boolean roundEnded = false;


    public UNOGameStateSnapShot(ArrayList<Player> currentPlayers, ArrayList<Card> currentDrawPile, ArrayList<Card> currentDiscardPile, boolean lightMode, Player currentPlayer, boolean currentDirection){
        this.currentPlayers = createStateSnapShotPlayers(currentPlayers);
        this.drawPile = new ArrayList<>(drawPile);
        this.discardPile = new ArrayList<>(discardPile);
        this.currentPlayer = currentPlayer;
        this.lightMode = lightMode;
        this.direction =  currentDirection;

    }

    private ArrayList<Player> createStateSnapShotPlayers(ArrayList<Player> currentPlayers){
        ArrayList<Player> playersStateSnapShot = new ArrayList<>();
        for (Player player : currentPlayers){
            playersStateSnapShot.add(player);
        }
        return playersStateSnapShot;
    }


}
