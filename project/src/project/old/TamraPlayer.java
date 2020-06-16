/**
 * CPSC 470
 * June 2020
 * @author Tamra Arant
 */

package project.old;

public class TamraPlayer implements SamplePlayer {

    private int bank;
    private String name;

    public TamraPlayer(int bank) {
        this.bank = bank;
        this.name = "Tamra";
    }

    public String getPlayerName() {
        return this.name;
    }

    @Override
    public boolean doesPlayerHit(String[] playerCards, String dealerUpCard) {

        int points = BlackjackRules.countPoints(playerCards);
        String dealerDownCard = "0";
        String[] dealerCard = {dealerUpCard + dealerDownCard};
        int dealerUpPoint = BlackjackRules.countPoints(dealerCard);
        if (points < 15) {
            return true;
        }
        else if (points < 16 && dealerUpPoint > 9) {
            return true;
        }
        return false;
    }

    @Override
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

    @Override
    public void modifyBank(boolean win, int amount) {
        if (win) {
            this.bank = this.bank + amount;
        } else {
            this.bank = this.bank - amount;
        }
    }


}
