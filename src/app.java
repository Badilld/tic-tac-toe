import java.util.Scanner;

public class app {
    /*
     * Tic-Tac-Toe
     * app.java
     * Created By: Badilld
     * CSCI 402 - Program 1
     * Notes: Starts game of Tic-Tac-Toe with depth-limited minimax AI
     * 
     */

    //main - start here
     public static void main(String[] args) throws Exception {
        display.selectOpponent();
        Scanner in = new Scanner(System.in);
        //Check that input is valid, int 1 or 2
        if (in.hasNextInt()) {
            int num = in.nextInt();
            if (num == 1 || num == 2){
                game.gameController(num);
            }else{
                System.out.println("Incorrect input. Please try again.");
            }
        } else {
            System.out.println("Incorrect input. Please try again.");
        }
        in.close();
    }
}
