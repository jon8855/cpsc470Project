/**
 * @author DTL
 */
package project.old;

import java.io.Serializable;

public class LambertsonPlayer implements SamplePlayer, PlayerMindState, Serializable{

	private static final long serialVersionUID = 1L;
	private int bank;
    private String name;
	private int consecutiveWins;
	private boolean isFeelingLucky;

	public LambertsonPlayer(int bank) {
        this.bank = bank;
        this.name = "Lambertson";
    }
    
    public String getPlayerName(){
        return this.name;
    }

	public boolean doesPlayerHit(String[] playerCards, String dealerUpCard) {
        int points = BlackjackRules.countPoints(playerCards);
        boolean dealerHighCard = (dealerUpCard.equals("A") || dealerUpCard.equals("K") || dealerUpCard.equals("Q") || dealerUpCard.equals("J") || dealerUpCard.equals("10"));
		if(points <= 12){
            return true;
        }
        else if(points <= 15 && dealerHighCard){
            return true;
        }
		return false;
	}

	public int placeBet(int bank, String[] playedCards, int numCardsLeft) {
	    isFeelingLucky();
		int bet;
		if (isFeelingLucky) {
			//goes for a big bet if they're on a hot streak
			bet = this.bank / 2;
		} else {
			bet = 10;
		}
		// change your bet amount here if you wish
				
		if (bet> this.bank)
			bet = this.bank;
		return bet;
	}

	public int getBank() {
		return bank;
	}

	public void modifyBank(boolean win, int amount) {
		if (win) {
			this.consecutiveWins += 1;
			this.bank = this.bank + amount;

		} else {
			this.consecutiveWins = 0;
			this.bank = this.bank - amount;
		}
	}

	//implements the State pattern, because this interface will influence how the object behaves
	public boolean isFeelingLucky() {
		//did 2 because it should be a rare occurrence to win
		// more than 2 in a row, therefore I would be feeling lucky
		if (this.consecutiveWins >= 2) {
			this.isFeelingLucky = true;
            System.out.println("David is feeling lucky!!");
		}
		else {
			this.isFeelingLucky = false;
		}
		return this.isFeelingLucky;
	}

}