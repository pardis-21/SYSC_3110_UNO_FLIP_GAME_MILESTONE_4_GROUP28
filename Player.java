import java.util.ArrayList;


public class Player {
    private final String name;
    private final ArrayList<Card> hand; //cards currently in the players hand

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    // get the players hand, it will return a list of cards the player is holding
    public ArrayList<Card> getHand(){
        return hand;
    }

    public void drawCard(){
        //the pile to be infinite
        Card newCard = new Card();
        hand.add(newCard);
        System.out.println(name + "drew a card: " + newCard);


    }

    // show players hand

    //chosing the card to play


    //if the player has one card left say uno if they have no cards left they win








}
