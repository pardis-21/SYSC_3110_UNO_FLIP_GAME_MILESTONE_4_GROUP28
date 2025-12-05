public class PlayerCardCommand implements UNOCommand{

    private GameLogicModel gameLogicModel;
    private Player currentPlayer;
    private Card currentTopCard;
    private Card prevoiusTopCard;
    private Player previousCurrentPlayer;


    public PlayerCardCommand(GameLogicModel model, Player player, Card cardPlayer, Card previousCard) {
        this.gameLogicModel = model;
        this.currentPlayer = player;
        this.currentTopCard = cardPlayer;
        this.prevoiusTopCard = previousCard;
        this.previousCurrentPlayer = player;
    }


    @Override
    public void undoAction(){
        //remove the card from the card thats in the discard pile and then place
        //it back into the players hand (or current players hand cards)
        gameLogicModel.getDiscardPile().removeLast();
        currentPlayer.getHand().add(currentTopCard);

        if (prevoiusTopCard != null) {
            gameLogicModel.setTopCard(prevoiusTopCard);
            gameLogicModel.getDiscardPile().add(prevoiusTopCard);
        }

        gameLogicModel.setCurrentPlayer(previousCurrentPlayer);

        gameLogicModel.notify();


    }
    public void redoAction(){

        previousCurrentPlayer.getHand().remove(prevoiusTopCard);

        gameLogicModel.getDiscardPile().remove(prevoiusTopCard);
        gameLogicModel.setTopCard(currentTopCard);
        gameLogicModel.setCurrentPlayer(previousCurrentPlayer);
        gameLogicModel.getDiscardPile().add(currentTopCard);
        //advance to the next player
        gameLogicModel.playerTurn();
        gameLogicModel.notify();
    }
}
