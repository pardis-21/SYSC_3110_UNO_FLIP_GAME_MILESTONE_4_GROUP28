import java.util.*;

/**
 * The Game class runs the main UNO game.
 * It handles player setup, game rounds, and determines the winner
 * once a player reaches 500 points.
 * This class uses PlayerOrder and Gamelogic to manage turns and rules.
 *

 *
 */
public class Game {

    public Game() {

    }

    public static void main(String[] args) {
            GameLogicModel model = new GameLogicModel();
            model.initializePlayers();

            UnoViewFrame view = new UnoViewFrame(model);

            UnoController controller = new UnoController(model);

            view.setController(controller);
            controller.setView(view);
        }

}
