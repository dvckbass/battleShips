package test;

import java.util.Scanner;

public class BattleShips {
	static int[][] board = new int[10][10];
	static Scanner input = new Scanner(System.in);
	static int x = 0;
	static int y = 0;
	static int playerLives = 5;
	static int compLives = 5;
	int random;

	public static void main(String[] args) {
		BattleShips battleShip = new BattleShips();
		battleShip.init();
		System.out.println("Deploy your ships:");
		battleShip.playerInput(x, y);
		battleShip.compInput();
		while((playerLives != 0) && (compLives != 0)) {
		battleShip.playerTurn();
		battleShip.computerTurn();
		}
		System.out.println("DONE");

	}

	public void init() {
		System.out.println("**** Welcome to Battle ships game ****\n");
		System.out.println("Right now, the sea is empty.\n");
		printBoard();
	}

	//check if the coordinates are valid
	public void checkCoord(int x, int y, int i) {
		if (this.x > 9 || this.y > 9) {
			while (this.x > 9 || this.y > 9) {
				System.out.println("Out of bonds. Type new values:");
				System.out.print("Enter X coordinate for your " + i + ".ship: ");
				this.x = input.nextInt();
				System.out.print("Enter Y coordinate for your " + i + ".ship: ");
				this.y = input.nextInt();
			}
		}

		while (board[this.x][this.y] == 1) {
			System.out.println("Occupied");
			inputCoord(x, y, i);
		}

	}

	//input the coordinates for ships from player
	public void inputCoord(int x, int y, int i) {
		System.out.print("Enter X coordinate for your " + i + ".ship: ");
		this.x = input.nextInt();
		System.out.print("Enter Y coordinate for your " + i + ".ship: ");
		this.y = input.nextInt();
		checkCoord(x, y, i);
	}

	//print the board 
	public void printBoard() {
		System.out.println("   0123456789   ");
		for (int r = 0; r < board.length; r++) {
			System.out.print(r + " |");
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c] == 1) {
					System.out.print("@");
				} else if(board[r][c] == 10) {
					System.out.print("!");
				} else if(board[r][c] == 11) {
					System.out.print("x");
				} else if(board[r][c] == 12) {
					System.out.print("-");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println("| " + r);
		}
		System.out.println("   0123456789   \n");
		System.out.println("Your ships: " + playerLives + " | Computer ships: " + compLives);
		System.out.println("-----------------------------------------------");
	}

	public void playerInput(int x, int y) {
		for (int i = 1; i <= 5; i++) {
			inputCoord(x, y, i);
			int a = this.x;
			int b = this.y;
			//System.out.println("board[" + a + "][" + b + "]: " + board[a][b]);
			/*
			 * while(board[x][y] == 1) { System.out.println("Occupied"); inputCoord(x, y,
			 * i); }
			 */
			board[a][b] = 1;
			//System.out.println("after board[" + this.x + "][" + this.y + "]: " + board[this.x][this.y]);
		}
		printBoard();
	}

	public void compInput() {
		System.out.println("Computer is deploying ships");
		int randX = (int) (Math.random() * 10);
		int randY = (int) (Math.random() * 10);
		for (int i = 1; i <= 5; i++) {
			randX = (int) (Math.random() * 10);
			randY = (int) (Math.random() * 10);
			while (board[randX][randY] == 1) {
				randX = (int) (Math.random() * 10);
				randY = (int) (Math.random() * 10);
			}
			board[randX][randY] = 2;
			//System.out.println("after board[" + randX + "][" + randY + "]: " + board[randX][randY]);
			System.out.println(i + ". ship DEPLOYED");
		}
	}
	
	public void playerTurn() {
		System.out.println("YOUR TURN");
		System.out.print("Enter X coordinate: ");
		int x = input.nextInt();
		System.out.print("Enter Y coordinate: ");
		int y = input.nextInt();
		if(board[x][y] == 2) {
			System.out.println("Boom! You sunk the ship!");
			board[x][y] = 10;
			compLives--;
		} else if(board[x][y] == 1) {
			System.out.println("Oh no, you sunk your own ship :(");
			board[x][y] = 11;
			playerLives--;
		} else {
			System.out.println("Sorry, you missed");
			board[x][y] = 12;
		}
		printBoard();
	}
	
	public void computerTurn() {
		System.out.println("COMPUTER'S TURN");
		int x = (int) (Math.random() * 10);
		int y = (int) (Math.random() * 10);
		if(board[x][y] == 1) {
			System.out.println("The computer sunk one of your ships!");
			board[x][y] = 11;
			playerLives--;
		} else if(board[x][y] == 2) {
			System.out.println("The computer sunk one of its own ships");
			board[x][y] = 10;
			compLives--;
		} else {
			System.out.println("Computer missed");
		}
		printBoard();
	}
}
