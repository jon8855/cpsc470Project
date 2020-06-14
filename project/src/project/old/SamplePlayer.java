/**
 * CPSC 110
 * Mar 1, 2017
 * I pledge
 * @author George
 */
//package project.old;

/**
 *
 */
public class SamplePlayer {

	private int bank;

	public SamplePlayer(int bank) {
		this.bank = bank;
	}

	public boolean doesPlayerHit(String[] playerCards, String dealerUpCard) {

		int points = BlackjackRules.countPoints(playerCards);
		// implement your strategy for deciding whether to hit or not

		return false;
	}

	public int placeBet(int bank, String[] playedCards, int numCardsLeft) {
		int bet = 10;
		// change your bet amount here if you wish
				
		if (bet>bank)
			bet = bank;
		return bet;
	}

	public int getBank() {
		return bank;
	}

	public void modifyBank(boolean win, int amount) {
		if (win) {
			this.bank = this.bank + amount;
		} else {
			this.bank = this.bank - amount;
		}
	}

}
