public class Card {

	String cardRank;
	String cardType;

	Card() {
		cardRank = "";
		cardType = "";
	}

	Card(String cr) {
		this.cardRank = cr;

	}

	Card(Card obj) {
		this.cardRank = obj.cardRank;
		this.cardType = obj.cardType;

	}

	void setRank(String cr) {
		this.cardRank = cr;

	}

	void setType(String ct) {
		this.cardType = ct;
	}

	String getRank() {
		return this.cardRank;
	}

	String getType() {
		return this.cardType;
	}
}
