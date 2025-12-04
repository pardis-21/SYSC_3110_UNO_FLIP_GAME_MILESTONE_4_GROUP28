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


    }
    public void redoAction(){
    }
}
