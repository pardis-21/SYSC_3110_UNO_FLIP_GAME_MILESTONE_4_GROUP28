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
        playerNode currentPlayer = firstPlayer;
        while (currentPlayer != null) {
            System.out.println("Its "+ currentPlayer.pName + "'s turn!");
            currentPlayer = currentPlayer.next;

        }
    }

    public void nextPlayerCounterClockwise(){
        playerNode currentPlayer = lastPlayer;
        while (currentPlayer != null) {
            System.out.println("Its "+ currentPlayer.pName + "'s turn!");
            currentPlayer = currentPlayer.prev;
        }

    }

    public void getAllPlayers() {
        playerNode currentPlayer = firstPlayer;
        if (currentPlayer != null) {
            while (currentPlayer != null) {
                currentPlayer = currentPlayer.next;
            }
        }
    }

    public int getTotalNumPlayers(){
        return numPlayers;
    }

    public Player getCurrentPlayer() {
        return currentPlayer.pName;
    }
}