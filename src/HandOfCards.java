import java.util.*;
import java.util.Vector;

public class HandOfCards {
	private Vector<Card> hand;

	HandOfCards() {
		hand = new Vector<Card>(7);
	}
	
	
	Vector<Card> getHand(){
		return this.hand;
	}

	/*
	* I- The original Deck.
	* 
	* P-Adds 7 cards to the player that calls the method,
	* and removes them from the deck.
	* 
	* O-None.
	* 
	*/
	
	
	
	void deal(Deck d) {
		for (int j = 0; j < 7; j++) {
			this.addCard(d.getDeck().elementAt(j));
			d.getDeck().remove(j);
		}
	}
	
void printHand() {
	for (int i = 0; i < this.hand.size(); i++) {
		System.out.println((i + 1) + ". " + this.hand.elementAt(i).cardRank
				+ " of " + this.hand.elementAt(i).cardType + " ");
		}
	}
	/*
	* I- Card object.
	* 
	* P-Refers to the player's hand and calls
	* the vector addElment method to add the 
	* card to the player's hand.
	* 
	* O-None.
	* 
	*/

	void addCard(Card c) {
		this.hand.addElement(c);
	}

	/*
	* I-None.
	* 
	* P- Creates a temporary vector with the size equal to the hand of the 
	* player and adds every card into it. A treeMap is created with a string 
	* key and integer value. It then goes into a loop which checks the 
	* frequency of each card by its cardRank field member. Each card's cardRank
	* field element is placed into the map as a key, with the frequency of that 
	* rank going into the value. When the whole hand has been iterated through,
	* the while-loop ends and a for-loop determines what card rank has
	* appeared four times, and returns that rank name.
	* 
	* 
	* O-None
	* 
	*/
	String detectBook() {
		boolean counting = true;
		int size = this.hand.size();
		Vector<String> sVect = new Vector<String>(size);

		for (int i = 0; i < size; i++) {
			sVect.addElement(this.hand.elementAt(i).cardRank);
		}

		TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();
		String word = null;
		int freq = 0;
		int x = 0;

		while (counting) {
			word = sVect.elementAt(x);
			tMap.put(word, freq);
			for (int j = 0; j < size; j++) {
				if (sVect.elementAt(j).equals(word)) { // counting freq here
					freq++;
					tMap.put(word, freq);
				}
			}
			freq = 0;
			x++;
			if (x == size) {
				counting = !counting;
			}
		}
		for (int k = 0; k < size; k++) {
			if (tMap.containsValue(4)) {
				if (tMap.get(sVect.elementAt(k)) == 4) {
					word = sVect.elementAt(k);
				}
			} else {
				word = null;
			}
		}
		return word;

	}
}
