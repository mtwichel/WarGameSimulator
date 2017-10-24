//class to simulate a card in the deck. the suits and face cards have
//number values associated with them
public class Card {
	private int suit;
	private String suitString;
	private int value;
	private String valueString;
	
	public Card(int suit, int value){
		this.suit = suit;
		this.value = value;
		if (suit == 0){
			this.suitString = "Hearts";
		}else if (suit == 1){
			this.suitString = "Diamonds";
		}else if (suit == 2){
			this.suitString = "Clubs";
		}else if (suit == 3){
			this.suitString = "Spades";
		}else{
			System.out.println("Your Stupid");
		}
		if (value == 10){
			valueString = "Jack";
		}else if (value == 11){
			valueString = "Queen";
		}else if (value == 12){
			valueString = "King";
		}else if(value == 13){
			valueString = "Ace";
		}else{
			valueString = "" + (this.value + 2);
		}
	}
	public int getSuit(){
		return this.suit;
	}
	public int getValue(){
		return this.value;
	}
	public String toString(){
		return "|" + valueString + " of " + suitString + "|";
	}
	public static void main(String[] args){
		Card card = new Card(2, 7);
		System.out.println(card);
	}
}
