import java.util.Stack;

public class Player {

    private Card[] board;               // an array representing the playing board, can range from 10 cards to 2
    private boolean[] boardFilled;      // tells if the player has found all cards and won the round/game
    // size of the board, closeCalls tracks how many times a player lost when one card away from winning
    private int boardSize, wins, wildcardsUsed, roundsPlayed, closeCalls;
    private String name;                // player name

    public Player(int size){
        name = null;
        boardSize = size;
        board = new Card[boardSize];
        boardFilled = new boolean[boardSize];
        // for stats
        wins = 0;
        wildcardsUsed = 0;
        roundsPlayed = 0;
        closeCalls = 0;
    }

    public String getName(){return name;}

    public void setName(String newName){name = newName;}

    public int getBoardSize(){return boardSize;}

    public boolean[] getBoardFilled(){return boardFilled;}

    public Card[] getBoard(){return board;}


    /**
     * Sets a player's board for a game
     * @param deck the deck to get the cards from
     */
    public void setBoard(Deck deck){
        board = new Card[boardSize];
        boardFilled = new boolean[boardSize];
        for(int i = 0; i < board.length; i++)
            board[i] = deck.drawCard();
    }

    /**
     * At the start of a turn, a player will check if the top card in the discard pile is a card that they need
     * @param pile a Stack of cards that have been discarded from previous turns
     * @return the card from the discard pile if the player needs it, null otherwise
     */
    public Card checkDiscardPile(Stack<Card> pile){
        if(!pile.isEmpty()){
            if((pile.peek().getNumber() <= board.length) && !boardFilled[pile.peek().getNumber() - 1])
                return pile.pop();
        }
        return null;
    }

    /**
     * Places a given card on the player's board
     * @param card the Card being added to the board
     * @param isKing true if the given card is a king, false otherwise
     * @return the card that was on the board before the current one was placed
     */
    public Card placeCard(Card card, boolean isKing){
        int cardIndex = 20;
        if(isKing){
            for(int i = 0; i < boardFilled.length; i++){
                if(!boardFilled[i])
                    cardIndex = i;
            }
            wildcardsUsed++;
        }
        else
            cardIndex = card.getNumber() - 1;

        Card coveredCard = board[cardIndex];
        board[cardIndex] = card;
        boardFilled[cardIndex] = true;
        return coveredCard;
    }

    public boolean playTurn(Deck deck, Stack<Card> discard) {
        // if the deck is empty, refill it and empty discard pile
        if(deck.getCardsLeft() == 0){
            discard.clear();
            deck = new Deck();
            deck.shuffle();
        }

        Card currentCard;
        Card discardTop = checkDiscardPile(discard);
        if (discardTop != null)
            currentCard = discardTop;
        else
            currentCard = deck.drawCard();

        if (currentCard.getNumber() <= board.length) {
            if (!boardFilled[currentCard.getNumber() - 1]) {
                // card is placed on board, card underneath becomes new card in play
                currentCard = placeCard(currentCard, false);
            }
        }
            // card is a wildcard
        else if (currentCard.getNumber() == 13) {
                // card is placed on board, card underneath becomes new card in play
            currentCard = placeCard(currentCard, true);
            }

        try {
            if (!boardFilled[currentCard.getNumber() - 1] || currentCard.getNumber() != 13) {
                while (!boardFilled[currentCard.getNumber() - 1] || currentCard.getNumber() == 13) {
                    if (currentCard.getNumber() == 13)
                        currentCard = placeCard(currentCard, true);
                    else {
                        currentCard = placeCard(currentCard, false);
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) { }

        discard.add(currentCard);
        return playerWon();
        }


    /**
     * Checks if a player has gotten all cards they need
     * @return true if all values in boardFilled are true, false otherwise
     */
    public boolean playerWon(){
        for (boolean b : boardFilled) {
            if (!b)
                return false;
        }
        return true;
    }


    /**
     * Prints the player's board to the console.
     * Any face up cards are specified, facedown cards are
     * identified as '*'
     */
    public void displayBoard(){
        String boardString = "";
        for(int i = 0; i < board.length; i++){
            boardString += "[ ";
            if(boardFilled[i]) {
                boardString += board[i].getNumber();
                boardString += " ]  ";
            }
            else
                boardString += "* ]  ";
        }
        System.out.println(boardString);
    }

    /* Functions for stats*/

    public void updateWins(){
        wins++;
        boardSize--;
    }

    public void updateRoundsPlayed(){roundsPlayed++;}

    public void updateCloseCalls(){
        int numFalse = 0;
        for(boolean b: boardFilled){
            if(!b)
                numFalse++;
        }
        if(numFalse == 1)
            closeCalls++;
    }

    public void displayStats(){
        System.out.println(name);
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Wins: " + wins);
        System.out.println("Rounds lost when one card away: " + closeCalls);
        System.out.println("Wildcards used: " + wildcardsUsed + "\n");
    }
}
