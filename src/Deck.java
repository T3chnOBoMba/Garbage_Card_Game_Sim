import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsRemaining;

    public Deck(){
        cardsRemaining = 52;
        cards = new ArrayList<>();

        // add each card to deck
        Card cardHeart, cardDiamond, cardSpade, cardClub;
        for(int i = 1; i < 14; i++){
            cardHeart = new Card(i, "heart");
            cardDiamond = new Card(i, "diamond");
            cardSpade = new Card(i, "spade");
            cardClub = new Card(i, "club");
            cards.add(cardHeart);
            cards.add(cardDiamond);
            cards.add(cardSpade);
            cards.add(cardClub);
        }
    }

    public int getCardsLeft(){return cardsRemaining;}

    /**
     * Helper function for shuffle that checks if all values in the given array are true
     * @param array a boolean array
     * @return true if all elements are true, false otherwise
     */
    public static boolean allIndicesVisited(boolean[] array){
        for(int i = 0; i < array.length; i++){
            if(!array[i])
                return false;
        }
        return true;
    }


    /**
     * Shuffle the cards in the deck
     */
    public void shuffle(){
        ArrayList<Card> newCards = new ArrayList<>();
        boolean[] card = new boolean[52];   // tells if card at given index has been added to new list
        int index;

        // while not all cards have been looked at, pick a random one to put on top
        while(!allIndicesVisited(card)){
            index = (int)(Math.random() * 52);
            if(!card[index]){
                card[index] = true;
                newCards.add(cards.get(index));
            }
        }
        cards = newCards;
    }

    /**
     * Prints the deck in order
     */
    public void printDeck(){
        for(Card card: cards){
            if(card.isFaceCard()){
                String face;
                if(card.getNumber() == 1)
                    face = "A";
                else if(card.getNumber() == 11)
                    face = "J";
                else if(card.getNumber() == 12)
                    face = "Q";
                else
                    face = "K";
                System.out.println(face + ":" + card.getSuit());
            }
            else
                System.out.println(card.getNumber() + ":" + card.getSuit());
        }
    }

    /**
     * Draws a card from the top of the deck
     * @return a card from the top of the deck
     */
    public Card drawCard(){
        Card cardDrawn = cards.get(0);
        cards.remove(cardDrawn);
        cardsRemaining--;
        return cardDrawn;
    }
}
