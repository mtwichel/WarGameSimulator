import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//this class simulates a deck of cards. it contains some methods I needed for the game
//but it could be changed to serve many card game applications

public class Deck {
	
	private List<Card> deck;
	
	public Deck(){
		deck = new ArrayList<Card>();
		
	}
	
	public void buildDeck(int numCards){
		for (int x=0; x<4; x++){
			for (int y=0; y<numCards/4; y++){
				deck.add(new Card(x, y));
			}
		}
	}
	public void suffle(){
		List<Card> temp = new ArrayList<Card>();
		Random random = new Random();
		int tempDeckSize = deck.size();
		for (int x=0; x<tempDeckSize; x++){
			int tempNum = random.nextInt(deck.size());
			temp.add(deck.get(tempNum));
			deck.remove(deck.get(tempNum));
		}
		deck = temp;
	}
	
	
	public Card getCardAt(int position){
		return deck.get(position);
	}
	public Card removeTopCard(){
		Card tempCard = deck.get(0);
		deck.remove(0);
		return tempCard;
	}
	public void removeCardAt(int position){
		deck.remove(position);
	}
	public void addToBottom(Card card){
		deck.add(card);
	}
	public void removeCard(Card card){
		for(int x=0; x<deck.size(); x++){
			if (deck.get(x) == card){
				deck.remove(x);
			}
		}
	}
	public int size(){
		return deck.size();
	}
	
	public String toString(){
		String temp = "";
		for(int x=0; x<deck.size(); x++){
			temp = temp + deck.get(x) + "\n";
		}
		return temp;
	}
	public static void main(String[] args){
		Deck deck = new Deck();
		deck.buildDeck(52);
		deck.suffle();
		
		Card tempCard = deck.getCardAt(4);
		System.out.println(deck + "\n\n\n");
		System.out.println(deck.getCardAt(4) + "\n\n\n");
		deck.removeCard(tempCard);
		System.out.println(deck);
	}
}

