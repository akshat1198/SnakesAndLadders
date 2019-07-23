import java.util.*;
import java.util.Scanner;

public class SnakesLaddersGamePlay  {
	int end = 100;// Game ends at this position
	boolean game = true; // Runs the game
	int ladders[] = new int[5];
	int snakes[] = new int[5];
	int players;

	public SnakesLaddersGamePlay() {
		System.out.println("Welcome to the Snakes and Ladder Game!!!");
		checkSLconditions();
		sortDisplaySL();
		playGame();
		
	}

	public int assignSnakes() {
		int s = (int) (Math.random() * 80) + 15;
		return s;
	}

	public int assignLadders() {
		int l = (int) (Math.random() * 80) + 10;
		return l;
	}

	// Assign five snakes and five ladders to random positions
	public void checkSLconditions() {
		for (int i = 0; i < 5; i++) {
			ladders[i] = assignLadders();
			snakes[i] = assignSnakes();
			if (snakes[i] == ladders[i]) {
				snakes[i] = assignSnakes();

			}
		}

		// Reassign a location to snake if it is the same as a ladder's - part 1

		// Reassign a location to snake if it is the same as a ladder's - part 2
		for (int q = 0; q < 5; q++) {
			for (int r = 0; r < 5; r++) {
				if (snakes[r] == ladders[q]) {
					ladders[q] = assignLadders();
					snakes[q] = assignSnakes();

				}
			}
		}
		// Ensure a snake's position is different from another snake's position and likewise for ladders
		for (int k = 0; k < 5; k++) {
			for (int m = 0; m < k; m++) {
				if (snakes[m] == snakes[k]) {
					snakes[k] = assignSnakes();
				}
				if (ladders[m] == ladders[k]) {
					ladders[k] = assignLadders();
				}
			}
		}
		// Reassign ladders' location if player ends at another ladder after climbing and likewise for snakes'
		
		for (int d = 0; d < 5; d++) {
			for (int e = 1; e < 5; e++) {
				if (ladders[e] - ladders[d] == 10) { // || (ladders[i]+10 == snakes[e])
					ladders[d] = assignLadders();
				}
				if (snakes[e] - snakes[d] == 15) {
					snakes[d] = assignSnakes();
				}
		// Reassign ladders' location if player ends at a snake after climbing and vice-versa
				if ((ladders[e] + 10 == snakes[d]) || (snakes[e] - 15 == ladders[d])) {
					ladders[d] = assignLadders();
					snakes[d] = assignSnakes();

				}
			}
		}

	}

	// Sort the order of ladders and snakes positions
	public void sortDisplaySL() {
		for (int b = 0; b < 5; b++) {
			for (int c = 0; c < 4; c++) {
				if (ladders[b] < ladders[c]) {
					int ladtemp = ladders[b];
					ladders[b] = ladders[c];
					ladders[c] = ladtemp;
				}
				if (snakes[b] < snakes[c]) {
					int snaketemp = snakes[b];
					snakes[b] = snakes[c];
					snakes[c] = snaketemp;
				}
			}

		}

		// Display the positions where the ladders and snakes are
		for (int a = 0; a < 5; a++) {
			System.out.println("Ladder " + (a + 1) + " at " + ladders[a] + "\t Snake " + (a + 1) + " at " + snakes[a]);
		}

	}

	public int getPlayers() {
		
		Scanner scan3 = new Scanner(System.in);
		try{
			System.out.print("\nChoose a number of players between 2 and 5 and press ENTER: ");
		int players = scan3.nextInt();
		this.players = players;
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid choice of players! Please try again.");
			getPlayers();
		}
		if((players<2)||(players>5)) {
			System.out.println("Not the number of players allowed to play! Please try again.");
			getPlayers();
		}
		return this.players;
	}

	public int rollDice() {
		int d = (int) (Math.random() * 6) + 1;
		return d;
	}

	public void playGame() {
		getPlayers();
		System.out.println();
		System.out.println(players + " players are playing the game");
		System.out.println("\nKeep pressing ENTER to see the gameplay!");
		int position[] = new int[players];
		while (game) {
			for (int p = 0; p < players; p++) {

				System.out.println();
				
				Scanner x1 = new Scanner(System.in);
				x1.nextLine();// User must keep pressing ENTER key to see the game progress

				System.out.println("Player " + (p + 1) + " was at position " + position[p]);

				System.out.print("Player " + (p + 1) + " Dice number is ");
				int dice = rollDice();
				System.out.println(dice);
				// Restrict player from moving if the dice number is greater than the distance to the end
				if ((position[p] > 94) && (dice > (end - position[p]))) {
					dice = 0;
				}
				position[p] += dice;// Move player 1 as many steps as the dice says

				System.out.println("Player " + (p + 1) + " is at position " + position[p]);
				// Player's interaction with a snake or a ladder
				for (int sl = 0; sl < 5; sl++) {
					if (position[p] == ladders[sl]) {
						System.out.println("Climbing a ladder");
						position[p] += 10;
						System.out.println("Player " + (p + 1) + " is now at postion " + position[p]);
					} else if (position[p] == snakes[sl]) {
						System.out.println("Got bitten by snake");
						position[p] -= 15;
						System.out.println("Player " + (p + 1) + " is now at position " + position[p]);
					}
				}

				if (position[p] == end) {// Display Player as winner if it reaches 100 and end the game
					System.out.println("Player " + (p + 1) + " Wins");
					game = false;
					break;

				}
			} // Player Count For Loop Ends
		}

	}

}
