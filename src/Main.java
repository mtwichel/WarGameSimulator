import java.util.*;

import javax.print.attribute.standard.NumberOfInterveningJobs;

public class Main {

	public Deck deckA; //player 1
	public Deck deckB; //player 2

	private Deck tempDeckA = new Deck();
	private Deck tempDeckB = new Deck();

	private int currentTurnNum;
	private int warCount;
	private String winner;

	public Main(){
		//the constructor sets each players deck to be completely random
		currentTurnNum = 0;
		warCount = 0;

		deckA = new Deck();
		deckB = new Deck();

		Deck oDeck = new Deck();
		oDeck.buildDeck(52);
		oDeck.suffle();

		for (int x=0; x<52; x++){
			Card tempCard = oDeck.removeTopCard();
			if(x%2 == 0){
				deckA.addToBottom(tempCard);
			}else{
				deckB.addToBottom(tempCard);
			}
		}

	}

	public void playTurn(){
		
		if(!hasWinner()){ //make sure no winner has happened
			Card playerACard = deckA.removeTopCard(); //this method stores the top card and it's removed from their deck
			Card playerBCard = deckB.removeTopCard();


			if (playerACard.getValue() > playerBCard.getValue()){ //see who has the better card. is case player A
				if(!hasWinner()){
					deckA.addToBottom(playerACard);  //puts both cards into player A's deck
					deckA.addToBottom(playerBCard);

					for (int x=0; x<tempDeckA.size(); x++){ //this whole loop is for when a WAR occurs and there is more than 1 card to move
						deckA.addToBottom(tempDeckA.removeTopCard());
						deckA.addToBottom(tempDeckB.removeTopCard());
					}
				}
				
			}else if(playerBCard.getValue() > playerACard.getValue()){ //this is all the same just when player B wins
				if(!hasWinner()){
					deckB.addToBottom(playerBCard);
					deckB.addToBottom(playerACard);

					for (int x=0; x<tempDeckA.size(); x++){
						deckB.addToBottom(tempDeckA.removeTopCard());
						deckB.addToBottom(tempDeckB.removeTopCard());
					}
				}

			}else if(playerBCard.getValue() == playerACard.getValue()){ //this is when the cards tie and a WAR takes place
				warCount += 1;
				
				//I store the 3 cards from each player into a the temporary deck you saw before
				for (int x=0; x<3; x++){ 
					if(!hasWinner()){
						tempDeckA.addToBottom(deckA.removeTopCard());
					}
				}
				for (int x=0; x<3; x++){
					if(!hasWinner()){
						tempDeckB.addToBottom(deckB.removeTopCard());
					}
				}
				playTurn(); //recursive function that will continue to recurse until someone has a higher card
			}
		}
	}
	
	public boolean hasWinner(){ //simple method to see if someone ran out of cards. most the gameplay will stop because of this
		return deckA.size() == 0 || deckB.size() == 0;
	}
	public Deck getWinner(){ //simple method to see who that winner is
		if (deckA.size() == 0){
			return deckB;
		}else{
			return deckA;
		}
	}

	public void playGame(){
		
		//plays the game until someone looses and then sets a winner
		while(!hasWinner()){
			playTurn();
			currentTurnNum += 1;
		}
		if (getWinner() == deckA){
			System.out.print("Player A Wins!");
			this.winner = "A";
		}else{
			System.out.print("Player B Wins!");
			this.winner = "B";
		}
	}
	
	//self explanatory methods
	public int getCurrentTurnNum(){
		return currentTurnNum;
	}
	public int getWarTurns(){
		return this.warCount;
	}
	public String getWinnerString(){
		return this.winner;
	}
	public String toString(){
		String temp = "";
		temp = temp + deckA + "\n==========\n" + deckB;
		return temp;
	}

	public static void main(String [] args){
		
		//numbers for measuring statistics about the games
		long avgNumTurns = 0;
		long numWarTurns = 0;
		int numIterations = 10000;
		
		int playerACount = 0;
		int playerBCount = 0;

		for (int x=1; x<=numIterations; x++){ //plays through how ever many games are asked
			
			Main model = new Main();
			model.playGame();
			
			//number for metrics are stored
			avgNumTurns = (avgNumTurns + model.getCurrentTurnNum());
			numWarTurns = numWarTurns + model.getWarTurns();
			System.out.println(":  " + x);	
			if (model.getWinnerString().equals("A")){
				playerACount += 1;
			}else if(model.getWinnerString().equals("B")){
				playerBCount += 1; 
			}
			
			
		}
		//display results
		System.out.println("\n" + numIterations + " games were played");
		System.out.println("\nGames were an average of " + avgNumTurns/numIterations +" turns long");
		System.out.println("\nPlayer A Won about: " + Math.round((((double)playerACount/(double)numIterations) * (double)100)) + "% of the time.");
		System.out.println("Player B Won about: " + Math.round((((double)playerBCount/(double)numIterations) * (double)100)) + "% of the time.");
		System.out.println("\nWar Occured about " + Math.round((((double) numWarTurns / (double) avgNumTurns) * (double) 100)) + "% of the turns.");
	
	}

}
