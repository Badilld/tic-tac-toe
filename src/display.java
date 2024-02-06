import java.util.Scanner;

public class display {
    /*
     * Tic-Tac-Toe
     * display.java
     * Created By: Badilld
     * CSCI 402 - Program 1
     * Notes: This class handles all printing to terminal and gets input from humans
     * 
     */
    //Function that prints the board
    public static void printBoard(char[][] board){
        int j = 0;
        System.out.println("=================================================\n");
        for (int i = 0; i < 5; i++) {
            if (i == 0 || i == 2 || i == 4){
                System.out.println(" "+board[j][0]+" | "+ board[j][1] + " | "+board[j][2]+" ");
                j++;
            }
            else {
                System.out.println("---+---+---");
            }
        }
    }

    //Prints the formatting for a user input at start of program
    public static void selectOpponent(){
        System.out.println("=================================================\n");
        System.out.println("[1] Human");
        System.out.println("[2] AI");
        System.out.print("==> ");
    }

    //Prints a AI turn result
    public static void AITurn(int nodes, int playerNum, int x, int y) {
        System.out.println("\nPlayer "+playerNum+"'s turn...\n"+x+","+y+"\nNodes expanded = "+nodes+"\n");
    }
    
    //Prints the player turn result
    public static int[] playerTurn(int playerNum, char[][] board) {
        Scanner in = new Scanner(System.in);
        int[] xy = new int[2];
        System.out.println("\nPlayer "+playerNum+"'s turn...");
        while(true) {
            System.out.print("Enter row [0 to 2]: ");
            xy[0] = in.nextInt();
            System.out.print("Enter col [0 to 2]: ");
            xy[1] = in.nextInt();
            System.out.println(); //blank space
            if (board [xy[0]][xy[1]] != ' ') {
                System.out.println("That space is already occupied, try again.");
            } else {
                break; //Valid space
            }
        }
        return xy;
    }

    //Prints the victory message
    public static void victory(int playerNum, char[][] board) {
        printBoard(board);
        System.out.println("\nPlayer "+playerNum+" wins!\n");
    }

    //Prints the draw message
    public static void draw(char[][] board){
        printBoard(board);
        System.out.println("\nDraw!\n");
    }
}
