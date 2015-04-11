import java.util.Scanner;

public class GoFish {
	/*
* I-None.
* 
* P-The main method takes in the users input and for the
* type of game they want to play, creates 2 players then it creates
* a deck and shuffles it, then deals each player cards and puts 
* the remaining cards in the remaining deck. When the game starts
* it goes into a while loop that ends when either the deck runs out
* or either players' hands gets empty, when one of these conditions
* is met the game ends and the player with most books at the time 
* wins the game.
* 
* O-None
* 
*/

public static void main(String[] args) {
	try {
		Scanner sc = new Scanner(System.in);
		String s; // user input
do{
System.out.println("Welcome to the Go Fish game!");
System.out.println("Please choose your game option.");
System.out.println("Press \"1\" for a Human vs. Human game." + "\n"
	+ "Press \"2\" for a Human vs. Computer game.");
s = sc.nextLine();

if (s.contains("1")) {
System.out.println("Please Enter the 1st players name: ");
s = sc.nextLine();
Player p1 = new Player(s);
System.out.println("Please Enter the 2nd players name: ");
s = sc.nextLine();
Player p2 = new Player(s);
Deck d1 = new Deck();
d1.shuffleDeck();
p1.getplayerHand().deal(d1);
p2.getplayerHand().deal(d1);
RemainingDeck rd1 = new RemainingDeck();
rd1.placeRemainingDeck(d1);

while (!(rd1.getReDeck().isEmpty())) {
	while ((!checkHands(p1, p2) && !(rd1.getReDeck().isEmpty()))
			&& turn(p1, p2, rd1)) {

	}

	while ((!checkHands(p1, p2) && !(rd1.getReDeck().isEmpty()))
			&& turn(p2, p1, rd1)) {

	}
}

System.out.println("Game over");
if(p1.bookSize() > p2.bookSize()){
  System.out.println(p1.name + " Won! You scored " + p1.bookSize() +" Books.");
  System.out.println(p2.name + " You scored " + p2.bookSize() +" Books.");}
else{
  System.out.println(p2.name + " Won! You scored " + p2.bookSize() +" Books.");
  System.out.println(p1.name + " You scored " + p1.bookSize() +" Books.");}
} 

else if (s.contains("2")) {
System.out.println("Please Enter the 1st players name: ");
s = sc.nextLine();
Player p1 = new Player(s);
Player p2 = new Player("Computer");
Deck d1 = new Deck();
d1.shuffleDeck();
p1.getplayerHand().deal(d1);
p2.getplayerHand().deal(d1);

RemainingDeck rd1 = new RemainingDeck();
rd1.placeRemainingDeck(d1);


while (!(rd1.getReDeck().isEmpty())) {
	while ((!checkHands(p1, p2) && !(rd1.getReDeck().isEmpty()))
			&& turn(p1, p2, rd1)) {

	}

	while ((!checkHands(p1, p2) && !(rd1.getReDeck().isEmpty()))
			&& turn(p2, p1, rd1)) {

	}
}
System.out.println("Game over");
if(p1.bookSize() > p2.bookSize()){
  System.out.println(p1.name + " Won! You scored " + p1.bookSize() +" Books.");
  System.out.println(p2.name + " You scored " + p2.bookSize() +" Books.");
}
else{
  System.out.println(p2.name + " Won! You scored " + p2.bookSize() +" Books.");
  System.out.println(p1.name + " You scored " + p1.bookSize() +" Books.");
	}
	}
System.out.println("Do you wanna play again? Y or N");
s = sc.nextLine();
}while(s.equalsIgnoreCase("Y"));
//sc.close();
	System.exit(0);
	} catch (Exception ex) {
		System.out.print(ex);
	}
}


/*
* I-Player 1 and 2's objects, the Remaining Deck.
* 
* P-If the first player is the computer this method chooses a random
* card from his hand and calls askOtherPlayer() that specific 
* card to see if the other player has it if he does it returns true and 
* the player gets another turn, if not he draws a card from the deck, 
* in either case the addBook() method gets called to see if the player 
* has achieved a book at that moment.
* If the first player is a human it prompts the user to choose a card 
* from his hand and checks to see if its a valid input, if it is it
* calls askOtherPlayer()with that specific card and repeats the same 
* process as the computer case.
* 
* O-returns true if a card was given to another player so the player
* gets a bonus turn, and false if not.
* 
*/

static boolean turn(Player p1, Player p2, RemainingDeck rd)
		throws GameException {
	if (p1.name == "Computer") {
	int r = 0;
	boolean change = false;
	r = (int) (Math.random() * (p1.getHand().size()) - 1);
	Card cAsk = new Card(p1.getHand().elementAt(r).cardRank);
	change = (p1.askOtherPlayer(p2, cAsk));
	if (change)
		p1.addBook(p1.getplayerHand());
	else {
		p1.drawCard(rd);
		p1.addBook(p1.getplayerHand());
	}
	if (p1.getHand().lastElement() == cAsk)
		change = true;
	return change;
}
boolean change = false;

int in = 0;
String s = " ";
boolean input = false;
do {
input = false;
p1.printPlayerHand();
System.out.println("\nPlease choose the card you want to ask for!");
Scanner sc = new Scanner(System.in);
try{
if (sc.hasNextLine())
	s = sc.nextLine();
	if(!isNumeric(s))
		throw new GameException("Incorrect input");
	if(Integer.parseInt(s) > p1.handSize() || Integer.parseInt(s) < 0)
		throw new ArrayIndexOutOfBoundsException();
}
catch(ArrayIndexOutOfBoundsException ex){
	System.err.println("Input out of player's hand bounds!");
	input = true;
}
catch(GameException ex){
	System.err.println(ex);
	input = true;
}

} while (input);
		

in = (Integer.parseInt(s) - 1);
Card cAsk = new Card(p1.getHand().elementAt(in).cardRank);
System.out.println("You ask your opponent for any " 
		+ cAsk.getRank() + " cards.");
	change = p1.askOtherPlayer(p2, cAsk);
	if (change)
		p1.addBook(p1.getplayerHand());
	else {
		p1.drawCard(rd);
		p1.addBook(p1.getplayerHand());
	}
	if (p1.getHand().lastElement() == cAsk)
		change = true;
	return change;
}

/*
* I-Player 1 and 2's objects.
* 
* P-Checks to see if either player's hand is empty.
*  
* O-returns true if either players hands is empty.
* 
*/

static boolean checkHands(Player p1, Player p2) {
	return (p1.getHand().isEmpty() || p2.getHand().isEmpty());
}

/*
* I-A string taken from user input.
* 
* P-Checks to see if the users input is numeric character.
* 
* O-returns true if the string contains numeric digits, and false if not.
* 
*/
	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}
