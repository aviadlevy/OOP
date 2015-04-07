package ex1;
import il.ac.huji.cs.oop.mastermind.*;

public class Mastermind {

	public static void main(String[] args) {
		
		//define and reset the variables for the first time
		boolean resetStats = true;
		int numOfGamesPlayed = 0;
		int turnsToWin = 0;
		int numOfWin = 0;
		MastermindUI ui = MastermindUIFactory.newMastermindUI();
		double avgWinLen = 0;
		double winRate = 0;
		int length = -1;
		int numOfValues = -1;
		int numOfTurn = -1;
		boolean lostRound = true;
		
		while(true) {
			//reset the variables if the user asks to
			if(resetStats) {
				numOfGamesPlayed = 0;
				turnsToWin = 0;
				numOfWin = 0;
				avgWinLen = 0;
				winRate = 0;
				length = -1;
				numOfValues = -1;
				numOfTurn = -1;
				
				//check if inputs from user is good
				while(length <= 0) {
		    		length = ui.askNumber("Enter code length:");
			    	if(length <= 0 ) {
				    	ui.displayErrorMessage("Value must be positive:");
				    }
				}
				
				while(numOfValues <= 0) {
					numOfValues = ui.askNumber("Enter number of values:");
			    	if(numOfValues <= 0 ) {
				    	ui.displayErrorMessage("Value must be positive:");
				    }
				}
				
				while(numOfTurn <= 0) {
					numOfTurn = ui.askNumber("Enter max number of guesses:");
			    	if(numOfTurn <= 0 ) {
				    	ui.displayErrorMessage("Value must be positive:");
				    }
				}
			}
			
			ui.reset(length, numOfValues, numOfTurn);
			Code code =	CodeGenerator.newCode(length,numOfValues);
			
			for(int i=1; i<=numOfTurn; i++) {
				int bull = 0;
				int tempBull = 0;
				int cow = 0;
				int appearInCode = 0;
				int appearInGuess = 0;
				Code guess = ui.askGuess("Enter guess:",length);
				if(code.equals(guess)) {
					//if the user guessed right, update stats, and break
					numOfGamesPlayed++;
					numOfWin++;
					turnsToWin += i;
					lostRound = false;
					//if won the number of bulls is the same as length 
					ui.showGuessResult(guess, length, cow);
					ui.displayMessage("You won in "+i+" turns!");
					break;
				}
				
				lostRound = true;
				for(int j=0; j<numOfValues; j++) {
					/*go over all possible values and check how many time
					 *it appears in each code (guessed and real code).
					 *then check the amount of bulls and then we'll know
					 *the amount of cows. (minimum time of appearance minus bulls)*/
					appearInCode = 0;
					appearInGuess = 0;
					tempBull = 0;
					for(int k=1; k<=length; k++) {
						if(guess.getValue(k) == j) {
							appearInGuess++;
							if(guess.getValue(k) == code.getValue(k)) {
								tempBull++;
							}
						}
						if(code.getValue(k) == j) {
							appearInCode++;
						}	
					}
					if(appearInCode <= appearInGuess) {
						cow += (appearInCode  - tempBull);
					} else {
						cow += (appearInGuess  - tempBull);
					}
					
					bull += tempBull;
				}
				ui.showGuessResult(guess, bull, cow);
			}
			//if there is no winning in this round
			if(lostRound) {
				ui.displayMessage("You lost! You failed to find the code!");
				numOfGamesPlayed++;
			}
			//calculate the stats values
			if(numOfWin == 0) {
				avgWinLen = Double.NaN;
				winRate = 0;
			} else {
				avgWinLen = (double)turnsToWin/numOfWin;
				winRate = (double)numOfWin/numOfGamesPlayed;
			}
			
			ui.showStats(numOfGamesPlayed, numOfWin, winRate , avgWinLen);
			
			boolean playAgain = ui.askYesNo("Another game?");
			if(playAgain) {
				resetStats = ui.askYesNo("Do you want to change the game options?");
				
			} else {
				ui.close();
				break;
		    }
		}
	}
}