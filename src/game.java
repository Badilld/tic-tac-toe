import java.util.Random;

public class game {
    /*
     * Tic-Tac-Toe
     * game.java
     * Created By: Badilld
     * CSCI 402 - Program 1
     * Notes: This class handles all game logic
     */

    //Helper function for the creation of a new board
    private static char[][] intializeBoard(){
        char[][] newBoard = new char[3][3];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard.length; j++) {
                newBoard[i][j] = ' ';
            }
        }
        return newBoard;
    }

    //helper function that checks if a player has won the game. 
    //'X' means x wins
    //'O' means o wins
    //'N' means no winner
    public static char checkVictory(char[][] board) {
        int x = 0;
        int o = 0;
        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] == 'X' && board[i][1] == 'X' && board[i][2] == 'X') || (board[0][i] == 'X' && board[1][i] == 'X' && board[2][i] == 'X')){
                x++;
                break;
            }
            if ((board[i][0] == 'O' && board[i][1] == 'O' && board[i][2] == 'O') || (board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == 'O')) {
                o++;
                break;
            }
        }
        if ((board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') || (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X')) {
            x++;
        }
        if ((board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') || (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O')) {
            o++;
        }
        if (x == 1){ //X wins
            return 'X';
        } else if (o == 1) {
            return 'O'; //O Wins
        }else {
            return 'N'; //No Winner
        }
    }

    //The game controller. It manages the turn order, processes input from the players, and displays output
    public static void gameController(int input){
        char[][] newBoard = intializeBoard();
        Random rand = new Random();
        int[] xy = new int[3]; //xy position and node expanded
        char mark1 = 'X';
        char mark2 = 'O';
        int depth = 3; //This can be changed to modify difficulty (assignment spec = 3)
        // Randomly determine first player and set first player to X - second player to O
        int firstPlayer = rand.nextInt(2); // 0 or 1
        if (firstPlayer != 0) {
            mark1 = 'O';
            mark2 = 'X';
        }
        if(input == 1) { //Human vs AI
            aiVsHuman(firstPlayer, newBoard, mark1, mark2, xy, depth);
        }
        else { //AI vs AI
            aiVsAi(firstPlayer, newBoard, mark1, mark2, xy, depth);
        }
    }

    //Game logic for a human to play agains the ai
    private static void aiVsHuman(int firstPlayer, char[][] newBoard, char mark1, char mark2, int[] xy, int depth) {
        int freeSpaces = 9; //Count down to 0 as the game is played
        char status; //used to check for possible victory
        // Start game loop
        while (true) {
            if (firstPlayer == 0) {
                firstPlayer++;
                display.printBoard(newBoard);
                xy = display.playerTurn(1, newBoard);
                newBoard[xy[0]][xy[1]] = mark1;
            } else {
                firstPlayer--;
                display.printBoard(newBoard);
                xy = ai.evaluate(newBoard, depth, freeSpaces, mark2);
                newBoard[xy[0]][xy[1]] = mark2;
                display.AITurn(xy[2], 2, xy[0], xy[1]);
            }
            freeSpaces--;//Space filled
            status = checkVictory(newBoard); 
            //Check for winner
            if (status == 'X') {
                //X wins
                display.victory(1, newBoard);
                System.exit(0);
            } else if (status == 'O') {
                //O wins
                display.victory(2, newBoard);
                System.exit(0);
            }
            if (freeSpaces == 0) {
                //Draw
                display.draw(newBoard);
                System.exit(0);
            }
        }
    }

    //Game logic for a pair of ai players to play the game
    private static void aiVsAi(int firstPlayer, char[][] newBoard, char mark1, char mark2, int[] xy, int depth) {
        int freeSpaces = 9; // Count down to 0 as the game is played
        char status; // used to check for possible victory
        // Start game loop
        while (true) {
            if (firstPlayer == 0) {
                firstPlayer++;
                display.printBoard(newBoard);
                xy = ai.evaluate(newBoard, depth, freeSpaces, mark1);
                newBoard[xy[0]][xy[1]] = mark1;
                display.AITurn(xy[2], 2, xy[0], xy[1]);
            } else {
                firstPlayer--;
                display.printBoard(newBoard);
                xy = ai.evaluate(newBoard, depth, freeSpaces, mark2);
                newBoard[xy[0]][xy[1]] = mark2;
                display.AITurn(xy[2], 2, xy[0], xy[1]);
            }
            freeSpaces--;//Space filled
            status = checkVictory(newBoard);
            //Check for winner
            if (status == 'X') {
                //X wins
                display.victory(1, newBoard);
                System.exit(0);
            } else if (status == 'O') {
                //O wins
                display.victory(2, newBoard);
                System.exit(0);
            }
            if (freeSpaces == 0) {
                //Draw
                display.draw(newBoard);
                System.exit(0);
            }
        }
    }
}
