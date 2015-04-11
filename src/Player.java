import java.util.Scanner;
import java.util.Vector;

public class Player {

	String name;
	private HandOfCards playerHand;
	private Vector<Book> pBook = new Vector<Book>(13);

	Player(String n) {
		name = n;
		playerHand = new HandOfCards();
	}
	
	HandOfCards getplayerHand(){
		return this.playerHand;
	}
	
	Vector<Book> getPBook(){
		return this.pBook;
	}
	
	
	/*
* I-The Remaining Deck object.
* 
* P-Removes a card from the end of The Remaining Deck,
* and adds it to the players' hand.
*
* O-none.
* 
*/

void drawCard(RemainingDeck rd) {
	if (!rd.getReDeck().isEmpty()) {
		int i = rd.getReDeck().size() - 1;
		this.playerHand.addCard(rd.getReDeck().elementAt(i));
		rd.getReDeck().remove(i);
	}
}

void printPlayerHand() {
	System.out.println("Player " + this.name + ", this is your hand: ");
this.playerHand.printHand();
System.out.println("Player " + this.name + ", these are your books: ");
	for (int i = 0; i < pBook.size(); i++) {
		System.out.println(this.pBook.elementAt(i).bookRank);
	}
}
/*
* I-HandOfCards object-to access players' hand.
* 
* P-Calls detectBook() to see if a book was achieved
* and what rank was achieved, then it goes through 
* the players hand to remove all 4 elements of that
* rank and then it creates a Book object and adds it
* to pBook the vector for keeping track of each 
* player's set of books.
* 
*
* O-true if a book was achieved, false if not yet.
* 
*/
boolean addBook(HandOfCards h) {
	String rank = h.detectBook();
	int k = 0;
	if (rank != null) {
		while (k < 4) {
			for (int j = 0; j < h.getHand().size(); j++) {
				if (h.getHand().elementAt(j).cardRank == rank) {
					h.getHand().remove(j);
					k++;
				}
			}
		}
		this.getPBook().addElement(new Book(rank));
		System.out.println("\nPlayer " + this.name
+ " has acheived a book of " + rank + "'s.");
		return true;
	}
	return false;
}

int bookSize() {
	return this.pBook.size();
}

boolean isBookFull() {
	return this.pBook.isEmpty();
}

int handSize() {
	return this.playerHand.getHand().size();
}

Vector<Card> getHand() {
	return this.playerHand.getHand();
}


/*
* I-The object of the player that is being asked, and the asked card.
* 
* P-If the first player is the computer this method checks if the card 
* exists in his hand by calling checkCardExists() and finds where it is 
* in the hand by calling at(), then it adds it to the asker's hand and
* removes it from the player that is being asked then calls checkCardExists()
* to see if there is more cards of that rank if there is then it repeats the 
* process.
* If the first player is a human it asks the user if he has it the card if 
* he does it asks him to choose the position of the card in his hand and
* checks to see if its a valid input,if it is it then asks if he has 
* any more of that card. It  also checks if the user is lying about a 
* card existing in his hand.
* In either case when the player does'nt have a card it tells the 
* other one to Go Fish.
*
* O-returns true if a card was given to another player, and false if not.
* 
*/

boolean askOtherPlayer(Player p, Card ca) throws GameException {

	if (p.name == "Computer") {
p.printPlayerHand();
boolean more = false, change = false;
more = checkCardExists(ca, p);
while (more) {
	if (more) {
		int i = p.at(ca);
		this.playerHand.addCard(p.getHand().elementAt(i));
		p.playerHand.getHand().remove(i);
		change = true;
		more = checkCardExists(ca, p);
	}
}
if (!change) {
System.out.println(this.name+ "! Go Fish!" +
" Your opponent doesn't have any cards of this rank!");			
	}
	return change;
} 
	
else{
	p.printPlayerHand();
System.out.println("Player " + p.name+ 
		". Your opponent is asking for " + ca.getRank()+ ", do you have any?");


System.out.println("*** Enter \"Y\" if you do," +
		" and \"N\" if you do not. ***");
		
String s = " ";
Scanner sc = new Scanner(System.in);
if (sc.hasNextLine())
	s = sc.nextLine();
boolean change = false;
boolean givingCards = true;
do {
	if (s.equalsIgnoreCase("Y")) {
System.out.println("Please choose the number " +
"that contains the required card: ");
		
s = sc.nextLine();
if (GoFish.isNumeric(s)) {
	int i = (Integer.parseInt(s) - 1);

	this.playerHand.addCard(p.getHand().elementAt(i));
	p.getHand().remove(i);
	change = true;
}

p.printPlayerHand();
System.out.println("\nDo you have any more cards of rank "
+ ca.getRank() + " to give?");
System.out.println("*** Enter \"Y\" if you do," +
		" and \"N\" if you do not. ***");
if(sc.hasNextLine())
s = sc.nextLine();
while(((s.equalsIgnoreCase("Y")) != true) &&((s.equalsIgnoreCase("N")) != true))
 {
System.err.println("Please enter Y or N only!");
	s = sc.nextLine();
}
if (!(s.equalsIgnoreCase("Y"))) {
		givingCards = !givingCards;
	}
} 
else {
	if (s.equalsIgnoreCase("N")) {
	int size = p.getHand().size();
	for (int i = 0; i < size; i++) {
		if (p.getHand().elementAt(i).cardRank.equals(ca.getRank())) 
			{
			System.err.println("Cheater! You have atleast" +
			" one of the card being asked for!");
			s = "Y";
			change = true;
			givingCards = true;
	}
}

	if (!change) {
		System.out.println(this.name+ "! Go Fish!" +
		" Your opponent doesn't have any cards of this rank!");	
		return change;
			}		
		}
	}
} while (givingCards);
		return true;
	}
}

/*
* I-The asked card, and The object of the player that is being checked.
* 
* P-Checks to see if the asked card exists in the player's hand. 
* 
* O-returns true if the card exists in the player's hand, and false if not.
* 
*/

boolean checkCardExists(Card ca, Player p) {
	for (int i = 0; i < p.handSize(); i++) {
		if (p.getHand().elementAt(i).cardRank.equals(ca.cardRank)) {
			return true;
		}
	}
	return false;
}

/*
* I- Asked Card object.
* 
* P-Finds where in the hand the card exists.
*
* O-returns the index of where the card exists in the player's hand.
* 
*/
	
	int at(Card ca) {
		int index = 0;
		for (int i = 0; i < this.handSize(); i++)
			if (this.getHand().elementAt(i).cardRank.equals(ca.cardRank))
				index = i;
		return index;
	}

}
