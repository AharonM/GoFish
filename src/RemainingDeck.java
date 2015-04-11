import java.util.Vector;

public class RemainingDeck {
	private Vector<Card> reDeck;

	RemainingDeck() {
		reDeck = new Vector<Card>(38);
	}
	
	/*
	* I-The original shuffled Deck.
	* 
	* P-Places the original deck to the Remaining Deck vector.
	*
	* O-None
	*/
	void placeRemainingDeck(Deck d) {
		for (int i = 0; i < d.getDeck().size(); i++) {
			this.reDeck.addElement(d.getDeck().elementAt(i));
		}
	}

	Vector<Card> getReDeck() {
		return reDeck;
	}

}
