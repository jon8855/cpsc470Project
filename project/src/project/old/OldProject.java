/**
 * CPSC 110
 * Mar 1, 2017
 * I pledge
 * @author George
 */
package project.old;

import java.util.Random;
import java.util.Scanner;

/**
 * A program that can be used by students to test their Player algorithms against a randomly dealt
 * hand. The second line of the main() method must be changed by replacing "SamplePlayer" with the name of
 * the student's file (usually StudentLastNamePlayer)
 */
public class OldProject {
	public static void main(String[] args) {
		BlackJackSim game = new BlackJackSim();
		while (game.playAgain()) {
			game.run();
		}
	}
}
