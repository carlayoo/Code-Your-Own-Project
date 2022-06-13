import java.util.Scanner;

class Main {
	  
	public static void main(String[] args) {
		
		char[][] board = new char[3][3];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}

		Scanner in = new Scanner(System.in);
		System.out.println("Let's play Tic Tac Toe!");
		System.out.print("Player 1, what is your name? ");
		String p1 = in.nextLine();
		System.out.print("Player 2, what is your name? ");
		String p2 = in.nextLine();

		boolean player1 = true;

		boolean gameEnded = false;
		while(!gameEnded) {

			drawBoard(board);

			if(player1) {
				System.out.println(p1 + "'s Turn (x):");
			} else {
				System.out.println(p2 + "'s Turn (o):");
			}

			char c = '-';
			if(player1) {
				c = 'x';
			} else {
				c = 'o';
			}

			int row = 0;
			int col = 0;

			while(true) {
				
				System.out.print("Enter a row number (0, 1, or 2): ");
				row = in.nextInt();
				System.out.print("Enter a column number (0, 1, or 2): ");
				col = in.nextInt();

				if(row < 0 || col < 0 || row > 2 || col > 2) {
					System.out.println("This position is off the bounds of the board! Try again.");
				
				} else if(board[row][col] != '-') {
					System.out.println("Someone has already made a move at this position! Try again.");
				
				} else {
					break;
				}
			
			}

			board[row][col] = c;

			if(playerHasWon(board) == 'x') {
				System.out.println(p1 + " has won!");
				gameEnded = true;
			} else if(playerHasWon(board) == 'o') {
				System.out.println(p2 + " has won!");
				gameEnded = true;
			} else {

				if(boardIsFull(board)) {
					System.out.println("It's a tie!");
					gameEnded = true;
				} else {
					player1 = !player1;
				}

			}

		}
		
		drawBoard(board);

  }

	public static void drawBoard(char[][] board) {
		System.out.println("Board:");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public static char playerHasWon(char[][] board) {
		
		for(int i = 0; i < 3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return board[i][0];
			}
		}

		for(int j = 0; j < 3; j++) {
			if(board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return board[0][j];
			}
		}

		if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return board[0][0];
		}
		if(board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != '-') {
			return board[2][0];
		}

		return ' ';

	}

	public static boolean boardIsFull(char[][] board) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}

/*import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static void initBoard (HashMap<Integer, Character> board) {
        for (int i = 1; i <= 9; i++ ) {
            board.put(i, '_');
        }
    }

    static void displayBoard (HashMap<Integer, Character> board) {

        for (Integer i : board.keySet()) {
            if (board.get(i) == '_') {
                System.out.print(i);
            } else {
                System.out.print(board.get(i));
            }

            if (i % 3 != 0) {
                System.out.print(" | ");
            } else {
                System.out.println();
                if (i != 9) {
                    System.out.println("---------");
                }
            }
        }

        System.out.println();
    }

    static void greet() {
        System.out.println("WELCOME TO TIC-TAC-TOE");
    }

    static void showInstructions() {
        System.out.println("Instructions:");
        System.out.println("1. The game is played on a grid that's 3 squares by 3 squares.");
        System.out.println("2. You are X, your friend is O. Players take turns putting their marks in empty squares.");
        System.out.println("3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.");
        System.out.println("4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.");
    }

    static boolean isFilled (int key, HashMap<Integer, Character> board) {
        if(board.get(key) != '_') {
            return true;
        }

        return false;
    }

    static void fillBoard (int pos, HashMap<Integer, Character> board, char element) {
        board.replace(pos,element);
    }

    static char checkWinner(HashMap<Integer, Character> board) {
        int[][] winningConditions = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

        for (int[] numbers: winningConditions) {
            if (board.get(numbers[0]) == board.get(numbers[1]) && board.get(numbers[1]) == board.get(numbers[2])) {
                return board.get(numbers[0]);
            }
        }

        return '_';
    }

    static boolean checkDraw(HashMap<Integer, Character> board) {

        for (char value : board.values()) {
            if (value == '_') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // initialisation
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Character> board = new HashMap<Integer, Character>();
        initBoard(board);

        // greeting and displaying the board
        greet();
        System.out.println();
        displayBoard(board);

        System.out.println("Do you need any instructions(Y/N)?");
        System.out.print("> ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("Y")) {
            showInstructions();
        }

        System.out.println("First turn goes to X");

        char activePlayer = 'X';

        while (true) {

            System.out.println("Enter the place where you want to place " + activePlayer);
            System.out.print("> ");

            int pos = scanner.nextInt();

            if( pos > 9 && pos < 1) {
                System.out.println("Invalid Input");
            } else if (isFilled(pos, board)) {
                System.out.println("Sorry this field is already filled!");
            } else {
                fillBoard(pos, board, activePlayer);
                activePlayer = activePlayer == 'X' ? 'O' : 'X';

                displayBoard(board);

                if (checkWinner(board) == 'X') {
                    System.out.println("X WINS!!");
                    break;
                }

                if (checkWinner(board) == 'O') {
                    System.out.println("O WINS!!");
                    break;
                }

                if (checkDraw(board)) {
                    System.out.println("DRAW!!");
                    break;
                }
            }
        }


        scanner.close();
        System.out.println("Thank You!!");
    }
}*/