import java.util.Scanner;

public class SnakesAndLadder {

	public static void main(String[] args) {

		System.out.println("Welcome to the Snakes and Ladder Game" + "\n\nPress ENTER to begin\n");
		
		int end = 100;// Game ends at this position
		boolean game = true; // Runs the game
		int ladders[] = new int[5];
		int snakes[] = new int[5];

		// Assign five snakes and five ladders to random positions

		for (int i = 0; i < 5; i++) {
			ladders[i] = (int) (Math.random() * 80) + 10;

			snakes[i] = (int) (Math.random() * 80) + 15;
			// Reassign a location to snake if it is the same as a ladder's - part 1
			if (snakes[i] == ladders[i]) {
				snakes[i] = (int) (Math.random() * 80) + 15;
			}
		}
		// Reassign a location to snake if it is the same as a ladder's - part 2
		for (int q = 0; q < 5; q++) {
			for (int r = 0; r < 5; r++) {
				if (snakes[r] == ladders[q]) {
					ladders[q] = (int) (Math.random() * 80) + 10;
					snakes[q] = (int) (Math.random() * 80) + 15;
				}
			}
		}
		// Ensure a snake's position is different from another snake's position and
		// likewise for ladders
		for (int k = 0; k < 5; k++) {
			for (int m = 0; m < k; m++) {
				if (snakes[m] == snakes[k]) {
					snakes[k] = (int) (Math.random() * 80) + 15;
				}
				if (ladders[m] == ladders[k]) {
					ladders[k] = (int) (Math.random() * 80) + 10;
				}
			}
		}
		// Reassign ladders' location if player ends at another ladder after climbing
		// and likewise for snakes'
		// Reassign ladders' location if player ends at a snake after climbing and vice
		// versa
		for (int d = 0; d < 5; d++) {
			for (int e = 1; e < 5; e++) {
				if (ladders[e] - ladders[d] == 10) {// || (ladders[i]+10 == snakes[e])
					ladders[d] = (int) (Math.random() * 80) + 10;
					if (snakes[e] - snakes[d] == 15)
						snakes[d] = (int) (Math.random() * 80) + 15;
					if ((ladders[e] + 10 == snakes[d]) || (snakes[e] - 15 == ladders[d])) {
						ladders[d] = (int) (Math.random() * 80) + 10;
						snakes[d] = (int) (Math.random() * 80) + 15;
					}
				}
			}
		}
		// Sort the order of ladders and snakes positions

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

		Scanner scan3 = new Scanner(System.in);
		System.out.print("\nEnter the numbers of players in the game: ");
		int players = scan3.nextInt();

		System.out.println();

		System.out.println(players + " players playing the game");

		int position[] = new int[players];
		
		Scanner scan = new Scanner(System.in);

		while (game) {// Game Begins and keeps running until a winner is decided

			for (int p = 0; p < players; p++) {

				System.out.println();

				Scanner x1 = new Scanner(System.in);
				x1.nextLine();// User must keep pressing ENTER key to see the game progress

				System.out.println("Player " + (p + 1) + " was at position " + position[p]);

				System.out.print("Player " + (p + 1) + " Dice number is ");
				int dice = (int) (Math.random() * 6) + 1;
				System.out.println(dice);
				// Restrict player from moving if the dice number is greater than the distance
				// to the end
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
					game = false;break;
				}
			} // Player Count For Loop Ends

		} // Game ends

	}// Main
}// Class

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 * if (count % 2 == 0) {// Switch turn to player 1 when count has an even value
 * 
 * System.out.println();
 * 
 * Scanner scan1 = new Scanner(System.in); scan1.nextLine();// User must keep
 * pressing ENTER key to see the game progress
 * 
 * 
 * System.out.println("Player 1 was at position " + ppos1);
 * 
 * System.out.print("Player 1 Dice number is ");
 * 
 * // Roll Dice for Player 1 int dice1 = (int) (Math.random() * 6) + 1;
 * System.out.println(dice1); // Restrict player from moving if the dice number
 * is greater than the distance // to the end if ((ppos1 > 94) && (dice1 > (end
 * - ppos1))) { dice1 = 0; } ppos1 += dice1;// Move player 1 as many steps as
 * the dice says
 * 
 * System.out.println("Player 1 is at position " + ppos1); // Player 1's
 * interaction with a snake or a ladder for (int sl = 0; sl < 5; sl++) { if
 * (ppos1 == ladders[sl]) { System.out.println("Climbing a ladder"); ppos1 +=
 * 10; System.out.println("Player 1 is now at postion " + ppos1); } else if
 * (ppos1 == snakes[sl]) { System.out.println("Got bitten by snake"); ppos1 -=
 * 15; System.out.println("Player 1 is now at position " + ppos1); } }
 * 
 * if (ppos1 == end) {// Display Player 1 as winner if it reaches 100 and end
 * the game System.out.println("Player 1 Wins"); game = false; } count++;//
 * Increment count value } // Player 1 turn ends
 * 
 * 
 * if (count % 2 == 1) {// Switch turn to player 2 to when count is an odd
 * number
 * 
 * if (ppos1 < end) {// Player 2 should not be able to take turn if Player 1
 * already won the game
 * 
 * System.out.println(); Scanner scan2 = new Scanner(System.in);
 * scan2.nextLine();
 * 
 * System.out.println("Player 2 was at position " + ppos2);
 * System.out.print("Player 2 Dice number is ");
 * 
 * // Roll dice for player 2 int dice2 = (int) (Math.random() * 6) + 1;
 * System.out.println(dice2); // Restrict player from moving if the dice number
 * is greater than the distance // to the end if ((ppos2 > 94) && (dice2 > (end
 * - ppos2))) { dice2 = 0; }
 * 
 * ppos2 += dice2; // Move player 1 as many steps as the dice says
 * 
 * System.out.println("Player 2 is at position " + ppos2); // Player 2's
 * interaction with snake and ladder for (int sl = 0; sl < 5; sl++) {
 * 
 * if (ppos2 == ladders[sl]) {
 * 
 * System.out.println("Climbing a ladder"); ppos2 += 10;
 * System.out.println("Player 2 is now at postion " + ppos2);
 * 
 * } else if (ppos2 == snakes[sl]) { System.out.println("Got bitten by snake");
 * ppos2 -= 15; System.out.println("Player 2 is now at position " + ppos2); } }
 * // if (ppos2 == end) {// Display Player 2 as winner if it reaches 100 and end
 * the game System.out.println("Player 2 Wins"); game = false; }
 * 
 * } count++; } // Player 2 turn ends;
 * 
 * 
 */
