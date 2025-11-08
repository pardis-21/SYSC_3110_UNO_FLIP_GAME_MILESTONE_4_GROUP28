import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.InputMismatchException;

public class UnoFrame extends JFrame {

    Game game;

    JPanel cardPanel; // panel for current player's cards
    JPanel decksPanel; // panel for the discard pile, new card pile, and "UNO!" button
    JPanel scorePanel; // panel for the player's score

    public JButton discardPile;
    public JButton newCard;
    public JButton UNOButton;

    public UnoFrame(){
        game = new Game();

        while (true) {
            String userInput = JOptionPane.showInputDialog(null, "Enter the number of Players (2–4): ");
            game.numPlayers = Integer.parseInt(userInput);

            try {
                if (game.numPlayers < 2 || game.numPlayers > 4) {
                    JOptionPane.showMessageDialog(null, "Invalid number of players! Please enter 2–4.");
                } else
                    break;
            }
            catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Invalid number of players! Please enter 2–4.");
            }
        }

        while (game.players.size() < game.numPlayers) {
            String playerName = JOptionPane.showInputDialog(null, "Enter player name");

            //make sure 2 players by same name don't exist
            boolean exists = false;
            for (Player player : game.players) {
                if (player.getName().equals(playerName)) {
                    JOptionPane.showMessageDialog(null, "That player already exists!");
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                game.addNewPlayer(playerName);
            }
        }

        cardPanel = new JPanel();
        decksPanel = new JPanel();
        scorePanel = new JPanel();
        discardPile = new JButton();
        newCard = new JButton();
        UNOButton = new JButton();


//        //FIGURE OUT HOW TO DO FOR ALL PLAYERS
//        for(Card card: (game.players.get(0).getHand())) {
//            JButton cardButton = new JButton();
//            cardButton.setBackground(card.JavaCardColour(card.getCardColour()));
//            cardPanel.add(cardButton);
//        }


    }

}
