import java.util.Scanner;

public class TicTacToe {

    static char[][] board;
    static Scanner in = new Scanner(System.in);
    static boolean player1 = false;
    static int remainingMoves = 9;
    static char p1Char = 'O';
    static char p2Char = 'X';



    public static void main(String[] args) {
        makeBoard();
        printInstructions();

        while (remainingMoves > 0) {
            player1 = !player1;
            remainingMoves--;
            printGame();
            treatInput();
        }
        printEnd();
    }

    public static void printInstructions() {
        System.out.println("To play, simply write the coordinates you want to select"
        + "in the following form: \n\n"
        + "row column\n\n"
        + "Note: those are two integers separated by a space.\n"
        + "rows and columns starts at 1. Upper corner is  1 1\n\n");
    }

    public static void printGame() {
        System.out.println("\n--------------------");
        System.out.printf(player1 ? "Player 1's turn!!\n\n" : "Player 2's turn!!\n\n");
        System.out.println("Current Board:                Indexes:");
        printBoard();
        System.out.println("CHOOSE YOUR COORDINATES!");
        System.out.println("... And choose wisely.");
    }

    public static void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            // Prints Board
            for (int j = 0; j < 3; j++) {
                System.out.printf("%4c",TicTacToe.board[i][j]);
            }
            System.out.print("                |");
            // Prints Indexes of board, making it easier to play
            for (int j = 0; j < 3; j++) {
                System.out.printf("%7s", String.format("%d %d", i+1, j+1));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printEnd() {
        System.out.println("\n--------------------");
        System.out.println("\n\n");
        if (remainingMoves == 0) {
            System.out.println("IT'S A DRAW.");
        } else {
            System.out.printf(player1 ? "PLAYER 1 WON!!" : "\n\nPLAYER 2 WON!!");
        }
        System.out.println("\n\n");
        System.out.println("Current Board:                Indexes:");
        printBoard();
    }

    public static void makeBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '#';
            }
        }
    }

    // Takes in input then updates the board
    public static void treatInput() {
        boolean inputTaken = false;
        String[] theInput;
        int row = 1;
        int col = 1;
        char pChar;
        while (!inputTaken) {
            try {
                String[] theInputArray = in.nextLine().trim().split("\\s+");
                row = Integer.parseInt(theInputArray[0]) - 1;
                col = Integer.parseInt(theInputArray[1]) - 1;
                // More conditions
                if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
                    if (board[row][col] == '#') {
                        // If input is valid numbers, within dimensions and space is free
                        inputTaken = true;
                    } else {
                        System.out.println("This space is already occupied. No cheating!");
                        System.out.println("Please provide two whole numbers separated by a space.");
                    }
                } else {
                    System.out.println("Values of rows and columns must be between 1 and 3!");
                    System.out.println("Please provide two whole numbers separated by a space.");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Please provide two whole numbers separated by a space.");
            }
        }

        pChar =  player1 ? p1Char : p2Char;
        board[row][col] = pChar;
        if (thisPlayerWon(row, col, pChar)) remainingMoves = -1;
    }

    public static boolean thisPlayerWon(int row, int col, char pChar) {
        int cnt = 0;

        // Check row
        for (int i = 0; i < 3; i++) {
            if (board[row][i] == pChar) cnt++;
        }
        if (cnt == 3) return true;
        else cnt = 0;

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i][col] == pChar) cnt++;
        }
        if (cnt == 3) return true;
        else cnt = 0;

        // If applicable: Check diagonal
        if (row == col) {
            // Check left diagonal
            for (int i = 0; i < 3; i++) {
                if (board[i][i] == pChar) cnt++;
            }
            if (cnt == 3) return true;
            else cnt = 0;
        }
        if (row == 2 - col) {
            // Check right diagonal
            for (int i = 0; i < 3; i++) {
                if (board[i][2-i] == pChar) cnt++;
            }
            if (cnt == 3) return true;
        }

        return false;
    }

}

// javac TicTacToe.java && java TicTacToe
