/**
 * A class to represent an individual playing card
 */
public class Card {
    private String suit;    // suit of the card
    private int number;     // number of the card

    public Card(int num, String s){
        number = num;
        suit = s;
    }

    public int getNumber(){return number;}

    public String getSuit() {return suit;}

    /** @return true if the card is a king/queen/jack, false otherwise */
    public boolean isFaceCard(){return (number == 1 || number > 10);}
}
