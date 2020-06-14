/**
 * @author DTL
 */
//package project.old;

/**
 *
 */
public class LambertsonPlayer implements SamplePlayer{

    private int bank;
    private String name;

	public LambertsonPlayer(int bank) {
        this.bank = bank;
        this.name = "Lambertson";
    }
    
    public String getPlayerName(){
        return this.name;
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