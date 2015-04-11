import java.util.HashSet;
import java.util.Vector;

public class Deck {

	private Vector<Card> deck = new Vector<Card>(52);
	
	/*     Default Constructor
	* I-None
	* 
	* P-Creates 52 Card objects and puts them into 
	* the deck vector, then it assigns each card its
	* Rank and Type by nested for loops.
	*
	* O-None
	* 
	*/
Deck() {
	String tempType;
	for (int i = 0; i < 52; i++)
		deck.addElement(new Card());

	for (int i = 0; i < deck.size(); i++) {

		if (i < 13) {
			tempType = "Diamonds";
	} else if (i >= 13 & i < 26) {
		tempType = "Clubs";
	} else if (i >= 26 & i < 39) {
		tempType = "Hearts";
	} else {
		tempType = "Spades";
	}

	if ((i % 13) == 0) {
		deck.elementAt(i).setRank("Ace");
		deck.elementAt(i).setType(tempType);
	} else if ((i % 13) == 10) {
		deck.elementAt(i).setRank("Jack");
		deck.elementAt(i).setType(tempType);
	} else if ((i % 13) == 11) {
		deck.elementAt(i).setRank("Queen");
		deck.elementAt(i).setType(tempType);
	} else if ((i % 13) == 12) {
		deck.elementAt(i).setRank("King");
			deck.elementAt(i).setType(tempType);
		} else {
			deck.elementAt(i).setRank(Integer.toString((((i % 13) + 1))));
			deck.elementAt(i).setType(tempType);
		}
	}

}
	Vector<Card> getDeck(){
		return this.deck;
	}
	
	/* 
	* I-None
	* 
	* P-Creates a 52 Card temporary deck vector
	* and a HashSet, it then puts in a random element
	* from the original deck into the temporary and
	* records the index of the random element in the HashSet.
	* When its finished it clears the original deck, and transfers
	* all of the temporary decks elements back into the original. 
	*
	* O-None
	* 
	*/
	void shuffleDeck() {
		Vector<Card> tempDeck = new Vector<Card>(52);
		HashSet<Integer> prevNum = new HashSet<Integer>();

		int r = (int) (Math.random() * (52));
		boolean shuffling = true;
		while (shuffling) {
			if (prevNum.add(r)) {
				tempDeck.addElement(deck.elementAt(r));
			} else {
				if (tempDeck.size() == 52) {
					shuffling = !shuffling;
				}
			}
			r = (int) (Math.random() * (52));
		}

		deck.clear();
		deck = new Vector<Card>(52);
		for (int n = 0; n < 52; n++) {
			deck.addElement(tempDeck.elementAt(n));
		}

	}
}
