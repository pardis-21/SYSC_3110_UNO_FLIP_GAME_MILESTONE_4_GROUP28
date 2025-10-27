import java.util.ArrayList;

public class PlayerOrder {
    private playerNode firstPlayer;
    private playerNode lastPlayer;
    private int numPlayers;
    private playerNode currentPlayer;

    private class playerNode {
        private Player pName;
        private playerNode prev;
        private playerNode next;

        public playerNode(Player p) {
            this.pName = p;
            this.prev = null;
            this.next = null;

        }
    }

    public PlayerOrder() {
        this.firstPlayer = null;
        this.lastPlayer = null;
        this.numPlayers = 0;
        this.currentPlayer = null;
    }
    public boolean isEmpty() {
        return numPlayers == 0;
    }

    //adding players to the linkedList
    public void addPlayer(Player p) {
        playerNode tempPlayerNode = new playerNode(p);

        if (firstPlayer == null) {

            firstPlayer = tempPlayerNode;
            firstPlayer.next = firstPlayer;
            firstPlayer.prev = firstPlayer;

            lastPlayer = firstPlayer;
            lastPlayer.next = firstPlayer;
            lastPlayer.prev = lastPlayer;

            currentPlayer = firstPlayer;
        }
        else {

            lastPlayer.next = tempPlayerNode;
            tempPlayerNode.prev = lastPlayer;
            tempPlayerNode.next = firstPlayer;
            firstPlayer.prev = tempPlayerNode;
            lastPlayer = tempPlayerNode;

        }
        numPlayers++;
    }

    //traversing forward the doubly linked list
    public void nextPlayerClockwise(){
        if (currentPlayer != null) {
            //System.out.println("It's "+ currentPlayer.pName + "'s turn!");
            currentPlayer = currentPlayer.next;

        }
    }

    public void nextPlayerCounterClockwise(){
        if (currentPlayer != null) {
            //System.out.println("Its "+ currentPlayer.pName + "'s turn!");
            currentPlayer = currentPlayer.prev;
        }

    }

    public ArrayList<Player> getAllPlayersToArrayList() {
        ArrayList<Player> allPlayers = new ArrayList<>();

        playerNode arrayPlayer = firstPlayer;
        if (arrayPlayer == null) {
            return allPlayers;

        }
        for (int i = 0; i < numPlayers; i++){
            allPlayers.add(arrayPlayer.pName);
            arrayPlayer = arrayPlayer.next;
        }
        return allPlayers;
    }

    public int getTotalNumPlayers(){
        return numPlayers;
    }

    public Player getCurrentPlayer() {
       if (currentPlayer == null){
           return null;
       }
       else {
           return currentPlayer.pName;
       }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}