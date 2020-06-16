package project.old;

import javax.sound.midi.Soundbank;
import java.io.Serializable;

/**
 * CPSC 470
 * June 2020
 * @author Tamra Arant
 */

import java.io.Serializable;

public class TamraPlayer implements SamplePlayer, PlayerMindState, Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 5950169519310163575L;
    private int bank;
    private String name;
    private int consecutiveWins;
    private boolean isFeelingLucky;

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
        isFeelingLucky();
        int bet;
        if (isFeelingLucky) {
            //goes for a big bet if they're on a hot streak
            bet = 20;
        } else {
            bet = 10;
        }
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
            System.out.println("Tamara is feeling lucky!!");
        }
        else {
            this.isFeelingLucky = false;
        }
        return this.isFeelingLucky;
    }

}
