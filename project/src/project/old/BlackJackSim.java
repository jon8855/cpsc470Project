/**
 * @author DTL
 */
package project.old;

import java.util.Random;
import java.util.Scanner;

/**
 * A program that can be used by students to test their Player algorithms against a randomly dealt
 * hand. The second line of the main() method must be changed by replacing "SamplePlayer" with the name of
 * the student's file (usually StudentLastNamePlayer)
 * 
 * This is the facade and the singleton for the main class OldProject
 * 
 */
public class BlackJackSim {

	/**
	 * @param args - not used
	 */
	private boolean playAgain; // = true;
	private Scanner userIn; // = new Scanner(System.in);
	//this is where we implement the factory pattern
    private SamplePlayer player1; // = new LambertsonPlayer(100);
    private SamplePlayer player2; 
    private static BlackJackSim instance;
    
    private BlackJackSim(){
        this.playAgain = true;
        this.userIn = new Scanner(System.in);
        //this.player1 = new LambertsonPlayer(100); //temporary until we get the sockets running
        get2Players(); //gets the players for the game
        //this.player2 = getPlayer();
    }

    public static synchronized BlackJackSim getInstance(){
        if(instance == null){
            instance = new BlackJackSim();
        }
        return instance;
    }

	public void run() {

		// generate a random list of cards for a sample deck of 20
		int shoeSize = 20;
		int numCardsLeft = shoeSize;
		String[] deck = createNewDeck(shoeSize);
		String[] playedCards = new String[shoeSize];

		//players' names
		String p1Name = player1.getPlayerName();
		String p2Name = player2.getPlayerName();

		// place bets
		int bet1 = player1.placeBet(player1.getBank(), playedCards, numCardsLeft);
		System.out.println(p1Name + " Bank = " + player1.getBank());
		System.out.println(p1Name + " bets " + bet1 + "\n==========");

		//deal initial cards
		String[] playerCards = createEmptyHand(5);
		String[] dealerCards = createEmptyHand(5);
		String dealerUpCard = "";
		int nextShoeIndex = 0;
		int nextPlayerHandIndex = 2;
		int nextDealerHandIndex = 2;
		playerCards[0] = deck[0];
		dealerCards[0] = deck[1];
		playerCards[1] = deck[2];
		dealerCards[1] = deck[3];
		numCardsLeft -= 4;
		dealerUpCard = dealerCards[1];
		nextShoeIndex = 4;

		// player's hand
		boolean doesPlayerHit = true;
		while (doesPlayerHit) {
			printCurrentHandInfo(playerCards);
			doesPlayerHit = player1.doesPlayerHit(playerCards, dealerUpCard);
			if (doesPlayerHit) {
				playerCards[nextPlayerHandIndex] = deck[nextShoeIndex];
				nextShoeIndex++;
				numCardsLeft--;
				nextPlayerHandIndex++;
				System.out.println(p1Name + " hits.");
			}
			else
				System.out.println(p1Name + " stands.");
		}

		// dealer's hand
		boolean doesDealerHit = true;
		while (doesDealerHit) {
			printCurrentHandInfo(dealerCards);
			doesDealerHit = BlackjackRules.doesDealerHit(dealerCards);
			if (doesDealerHit) {
				dealerCards[nextDealerHandIndex] = deck[nextShoeIndex];
				nextShoeIndex++;
				numCardsLeft--;
				nextDealerHandIndex++;
				System.out.println("Dealer hits.");
			}
			else
				System.out.println("Dealer stands.");
		}

		int dealerPoints = BlackjackRules.countPoints(dealerCards);
		int playerPoints = BlackjackRules.countPoints(playerCards);
		System.out.println("==========\nDealer has: " + dealerPoints);
		System.out.println(p1Name + " has: " + playerPoints);

		// figure out winner - this is not correct for all cases, but it is close enough to test with
		boolean wonHand;
		if (dealerPoints > playerPoints && dealerPoints < 22
				|| playerPoints > 21) {
			System.out.println(p1Name + " lost!");
			wonHand = false;
			player1.modifyBank(wonHand, bet1);
		} else if (dealerPoints == 21) {// check dealer blackjack
			if (dealerCards[2]=="0") {
				if (playerPoints == 21 && playerCards[2]=="0") {
					System.out.println("Push game!");
				}
				else {
					System.out.println("Dealer has Blackjack!!!");
					player1.modifyBank(false, bet1);
				}
			} else if (playerPoints == dealerPoints){
				System.out.println("Push game!");
			}
		} else if(playerPoints == 21 && playerCards[2]=="0") { // check player blackjack
			System.out.println(p1Name + " has Blackjack!!!");
			player1.modifyBank(true, (int) 1.5*bet1);
		} else if (playerPoints == dealerPoints){
			System.out.println("Push game!");
		} else {
			System.out.println("Player won!");
			player1.modifyBank(true, bet1);
		}
		System.out.println("Player bank = " + player1.getBank());
		System.out.println("\n" + p2Name + "'s turn!!!\n");

		/// BEGINS PLAYER 2's Turn /////////////

		// generate a random list of cards for a sample deck of 20
		shoeSize = 20;
		numCardsLeft = shoeSize;
		deck = createNewDeck(shoeSize);
		playedCards = new String[shoeSize];

		//players' names
		p1Name = player1.getPlayerName();
		p2Name = player2.getPlayerName();

		// place bets
		int bet2 = player2.placeBet(player1.getBank(), playedCards, numCardsLeft);
		System.out.println(p2Name + " Bank = " + player2.getBank());
		System.out.println(p2Name + " bets " + bet2 + "\n==========");

		//deal initial cards
		playerCards = createEmptyHand(5);
		dealerCards = createEmptyHand(5);
		dealerUpCard = "";
		nextShoeIndex = 0;
		nextPlayerHandIndex = 2;
		nextDealerHandIndex = 2;
		playerCards[0] = deck[0];
		dealerCards[0] = deck[1];
		playerCards[1] = deck[2];
		dealerCards[1] = deck[3];
		numCardsLeft -= 4;
		dealerUpCard = dealerCards[1];
		nextShoeIndex = 4;

		// player's hand
		doesPlayerHit = true;
		while (doesPlayerHit) {
			printCurrentHandInfo(playerCards);
			doesPlayerHit = player2.doesPlayerHit(playerCards, dealerUpCard);
			if (doesPlayerHit) {
				playerCards[nextPlayerHandIndex] = deck[nextShoeIndex];
				nextShoeIndex++;
				numCardsLeft--;
				nextPlayerHandIndex++;
				System.out.println(p2Name + " hits.");
			}
			else
				System.out.println(p2Name + " stands.");
		}

		// dealer's hand
		doesDealerHit = true;
		while (doesDealerHit) {
			printCurrentHandInfo(dealerCards);
			doesDealerHit = BlackjackRules.doesDealerHit(dealerCards);
			if (doesDealerHit) {
				dealerCards[nextDealerHandIndex] = deck[nextShoeIndex];
				nextShoeIndex++;
				numCardsLeft--;
				nextDealerHandIndex++;
				System.out.println("Dealer hits.");
			}
			else
				System.out.println("Dealer stands.");
		}

		dealerPoints = BlackjackRules.countPoints(dealerCards);
		playerPoints = BlackjackRules.countPoints(playerCards);
		System.out.println("==========\nDealer has: " + dealerPoints);
		System.out.println(p2Name + " has: " + playerPoints);

		// figure out winner - this is not correct for all cases, but it is close enough to test with
		//boolean wonHand;
		if (dealerPoints > playerPoints && dealerPoints < 22
				|| playerPoints > 21) {
			System.out.println(p2Name + " lost!");
			wonHand = false;
			player2.modifyBank(wonHand, bet1);
		} else if (dealerPoints == 21) {// check dealer blackjack
			if (dealerCards[2]=="0") {
				if (playerPoints == 21 && playerCards[2]=="0") {
					System.out.println("Push game!");
				}
				else {
					System.out.println("Dealer has Blackjack!!!");
					player2.modifyBank(false, bet1);
				}
			} else if (playerPoints == dealerPoints){
				System.out.println("Push game!");
			}
		} else if(playerPoints == 21 && playerCards[2]=="0") { // check player blackjack
			System.out.println(p2Name + " has Blackjack!!!");
			player2.modifyBank(true, (int) 1.5*bet1);
		} else if (playerPoints == dealerPoints){
			System.out.println("Push game!");
		} else {
			System.out.println("Player won!");
			player2.modifyBank(true, bet1);
		}
		System.out.println(p2Name + "'s bank = " + player2.getBank());

		/// END PLAYER 2's turn ///////////////////////

        System.out.println("Play again? [Y/N]");
        String n = userIn.next();
		//if ((userIn.next().equals("Y") || (userIn.next().equals("y")))) {
        if(n.equals("Y") || n.equals("y")) {
			playAgain = true;
		} else {
            playAgain = false;
		}
	}

	/**
	 * @param handSize - the number of card slots in the hand
	 * @return an array of empty card slots
	 */
	private static String[] createEmptyHand(int handSize) {
		// create empty array
		String[] hand = new String[handSize];
		for (int i=0; i<handSize; i++) {
			hand[i] = "0";
		}
		return hand;
	}

	/**
	 * @param shoeSize - the number of cards in the "shoe". This is usually a multiple of 52
	 * @return an array of cards representing a deck of the specified number of cards
	 */
	private static String[] createNewDeck(int shoeSize) {

		String[] deck = new String[shoeSize];
		Random random = new Random();
		for (int i=0; i<shoeSize; i++) {
			int rNum = random.nextInt(13);
			switch (rNum) {
			case 0:
				deck[i] = "K";
				break;
			case 1:
				deck[i] = "A";
				break;
			case 2:
				deck[i] = "2";
				break;
			case 3:
				deck[i] = "3";
				break;
			case 4:
				deck[i] = "4";
				break;
			case 5:
				deck[i] = "5";
				break;
			case 6:
				deck[i] = "6";
				break;
			case 7:
				deck[i] = "7";
				break;
			case 8:
				deck[i] = "8";
				break;
			case 9:
				deck[i] = "9";
				break;
			case 10:
				deck[i] = "10";
				break;
			case 11:
				deck[i] = "J";
				break;
			case 12:
				deck[i] = "Q";
				break;
			}
		}
		return deck;
	}
	
	/**
	 * @param cards - an array of cards to be printed
	 */
	private static void printCurrentHandInfo(String[] cards) {
		
		int totalPoints = BlackjackRules.countPoints(cards);
		System.out.print("\nCurrent Hand\nCards:");
		for (String card : cards) {
			if (!card.equals("0")) {
				System.out.print(" " + card);
			}
		}
		System.out.println("\nPoints: " + totalPoints);
    }
    
    public boolean playAgain(){
        return this.playAgain;
    }

    private void get2Players(){
		BlackJackServer server = new BlackJackServer();
		this.player1 = server.getPlayerFromServer();
		this.player2 = server.getPlayer2FromServer();
	}
	
}