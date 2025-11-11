import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UnoController implements ActionListener {

    private UnoViewFrame viewFrame;
    private final GameLogicModel model;


    public UnoController(GameLogicModel model) {

        this.model = model;
    }

    public void setView(UnoViewFrame viewFrame) {
        this.viewFrame = viewFrame;
        viewFrame.drawPile.addActionListener(this);
        // frame.UNOButton.addActionListener(this);
        //viewFrame.updateTopCard(model.getTopCard());
        viewFrame.discardPile.addActionListener(this);
        viewFrame.nextPlayerButton.addActionListener(this);
        viewFrame.UNOButton.addActionListener(this);
        model.startGame();
        updateView();    }






    public void onCardClicked(Card heldCard) {
        if (model.isTurnCompleted()) {
            JOptionPane.showMessageDialog(null, "Your turn is complete! Click on the next player button.");
            return;
        }

        Card top = model.getTopCard();

        boolean success = model.tryPlayCard(heldCard);
        if (!success) {
            viewFrame.showMessage(
                    "You cannot play " + heldCard + " on " + model.getTopCard()
            );
            return;
        }

        updateView();
        model.setTurnCompleted(true);
        Card updated = model.getTopCard();
        Card.Type type = updated.getCardType();
        if(type == Card.Type.REVERSE || type == Card.Type.SKIP || type == Card.Type.DRAW_ONE || type == Card.Type.WILD_DRAW2){
            model.setTurnCompleted(false);
        }

    }

    public void onDrawClicked(){
        model.drawCardCurrentPlayer();
        updateView();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (viewFrame == null) {
            System.err.println("ViewFrame is not initialized yet!");
            return;
        }

        Object source = e.getSource();

        if (source == viewFrame.drawPile) {
            if (model.isTurnCompleted()) {
                JOptionPane.showMessageDialog(null, "Your turn is complete! Click on the next player button.");
                return;
            }
            onDrawClicked();
            model.setTurnCompleted(true);

        }
        else if (source == viewFrame.discardPile){
            JOptionPane.showMessageDialog(viewFrame,"Top card: " + model.getTopCard());
        }
        else if (source == viewFrame.nextPlayerButton) { // making sure player actually plays b4 going to next player
            if (!model.isTurnCompleted()) {
                viewFrame.showMessage("You must play or draw before ending your turn!");
                return;
            }
            if (model.getCurrentPlayer().getHand().size() == 1 && !model.getCurrentPlayer().UNOClicked) {
                JOptionPane.showMessageDialog(null, "You had 'uno' card and didn't click UNO before ending your turn! draw 2 :P");
                onDrawClicked();
                onDrawClicked();
            }

            model.setTurnCompleted(false);
            model.playerTurn();
            updateView();
        }
        else if (source == viewFrame.UNOButton) {
            if (!(model.getCurrentPlayer().getHand().size() == 1)) {
                JOptionPane.showMessageDialog(null, "Uh oh! You don't have 'uno' card! draw 2 :P");
                onDrawClicked();
                onDrawClicked();
                model.setTurnCompleted(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "UNOOOOO!!!");
                model.getCurrentPlayer().UNOClicked = true;
            }

        }





    }

    public void updateView() {
        if (viewFrame == null) {
            return;
        }
        else {
            //PlayerOrder playerOrder = new PlayerOrder();
            //List<Card> hand = model.getPlayerHand();
            viewFrame.updateHand(model.getPlayerHand());
            viewFrame.updateTopCard(model.getTopCard());
            viewFrame.currentPlayerName.setText(model.getCurrentPlayer().getName());
        }


    }

    public List<Card> getCurrentPlayerHand() {
        return model.getCurrentPlayer().getHand();
    }
}
