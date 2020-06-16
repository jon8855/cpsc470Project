/**
 * @author DTL
 */
package project.old;

/**
 *
 */
public interface SamplePlayer {

	//public int bank;

	//public SamplePlayer(int bank);

	public String getPlayerName();

	public boolean doesPlayerHit(String[] playerCards, String dealerUpCard);

	public int placeBet(int bank, String[] playedCards, int numCardsLeft);

	public int getBank() ;

	public void modifyBank(boolean win, int amount);

}
