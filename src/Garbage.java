import java.util.ArrayList;
import java.util.Stack;

public class Garbage {

    private Deck deck;
    private Stack<Card> discardPile;
    private ArrayList<Player> players;

    public Garbage(int numPlay){
        deck = new Deck();
        discardPile = new Stack<>();
        players = new ArrayList<>();

        Player player1, player2, player3, player4;
        player1 = new Player(10);
        player1.setName("Josh");
        players.add(player1);

        if(numPlay > 1) {
            player2 = new Player(10);
            player2.setName("Ellen");
            players.add(player2);
        }
        if(numPlay > 2) {
            player3 = new Player(10);
            player3.setName("Terry");
            players.add(player3);
        }
        if(numPlay > 3){
            player4 = new Player(10);
            players.add(player4);
        }
    }

    public static boolean cardIsAKing(Card card){return card.getNumber() == 13;}


    public void initializeGame(){
        deck = new Deck();
        deck.shuffle();
        for(Player player: players){
            player.setBoard(deck);
        }
    }


    public Player simGame(){
        boolean playerWon = false;
        while(!playerWon){
            for(Player player: players){
                playerWon = player.playTurn(deck, discardPile);
                if(playerWon)
                    return player;
            }
        }
        return null;
    }


    public boolean gameOver(){
        for(Player player: players){
            if(player.playerWon() && player.getBoardSize() == 1)
                return true;
        }
        return false;
    }


    public void simMatch(){
        Player victor = null;
        while(!gameOver()){
            initializeGame();
            victor = simGame();
            victor.updateWins();

            // update stats
            for(Player player: players) {
                player.updateRoundsPlayed();
                player.updateCloseCalls();
            }

            if(victor.getBoardSize() < 2) {

                System.out.println(victor.getName() + " WINS!\n");
                for(Player player: players) {
                    player.displayStats();
                }
                break;
            }
        }
    }


    public static void main(String[] args){
        Garbage game = new Garbage(3);
        game.simMatch();
    }
}
