public class ai {
    /*
     * Tic-Tac-Toe
     * ai.java
     * Created By: Badilld
     * CSCI 402 - Program 1
     * Notes: This class handles ai logic and depth limited search
     */

    //Function for evaluating the best move, given a depth limited search of the game tree
    //Returns result[0] = x-coord, result[1] = y-coord, result[2] = nodes expanded
    public static int[] evaluate(char[][] board, int depth, int empty, char mark) {
        int[] result = new int[3];
        int[] curr = new int[2];
        int max = -10;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = mark;
                    curr = miniMax(board, depth - 1, 0, empty - 1, mark, false);
                    //Compare minimax score
                    if (max <= curr[0]){
                        max = curr[0];
                        result[0] = i;
                        result[1] = j;
                    }
                    result[2] += curr[1]; // nodes expanded
                    board[i][j] = ' ';
                }
            }
        }
        return result;
    }

    //Solves for best possible move given board state
    private static int[] miniMax(char[][] board, int depth, int moves, int empty, char mark, boolean maximize) {
        int[] eval = new int[2]; 
        int[] temp = new int[2];
        int nodes = 0;
        char other = xOrO(mark);
        //Check for winner here
        if (game.checkVictory(board) == mark) {
            eval[0] = 10 - moves;
            eval[1] = nodes;
            return eval;
        } else if (game.checkVictory(board) == other) {
            eval[0] = -10 + moves;
            eval[1] = nodes;
            return eval;
        }

        //Draw
        if (empty == 0) {
            eval[0] = 0;
            eval[1] = nodes;
            return eval;
        }

        //If we are not a leaf
        if (depth != 0) {
            if (maximize) {
                eval[0] = -10;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == ' ') {
                            board[i][j] = mark;
                            nodes++;
                            temp = miniMax(board, depth - 1, moves + 1, empty - 1, mark, false);
                            eval[0] = Math.max(temp[0], eval[0]);
                            nodes += temp[1];
                            board[i][j] = ' ';
                        }
                    }
                }
            } else {
                eval[0] = 10;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == ' ') {
                            board[i][j] = other;
                            nodes++;
                            temp = miniMax(board, depth - 1, moves + 1, empty - 1, mark, true);
                            eval[0] = Math.min(temp[0], eval[0]);
                            nodes += temp[1];
                            board[i][j] = ' ';
                        }
                    }
                }
            }
        } else { //Calculate leaf value and return
            eval[0] = boardScore(board, mark);
        }
        eval[1] = nodes;
        return eval;
    }

    //Simple heuristic for determing if a given move advances our board state or stops another from advancing
    private static int boardScore(char[][] board, char mark) {
        int score = 0;
        char other = xOrO(mark);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //player
                if (j == 0) {
                    if(board[i][0] == mark && board[i][1] != other && board[i][2] != other){
                        score++;
                    }
                } else if (j == 1) {
                    if (board[i][1] == mark && board[i][0] != other && board[i][2] != other) {
                        score++;
                    }
                } else if (j == 2) {
                    if (board[i][2] == mark && board[i][1] != other && board[i][0] != other) {
                        score++;
                    }
                }
                if (i == 0) {
                    if (board[0][i] == mark && board[1][i] != other && board[2][i] != other) {
                        score++;
                    }
                } else if (i == 1) {
                    if (board[1][j] == mark && board[0][j] != other && board[2][j] != other) {
                        score++;
                    }
                } else if (i == 2) {
                    if (board[2][j] == mark && board[1][j] != other && board[0][j] != other) {
                        score++;
                    }
                }
                //opponent
                if (j == 0) {
                    if (board[i][0] == other && board[i][1] != mark && board[i][2] != mark) {
                        score--;
                    }
                } else if (j == 1) {
                    if (board[i][1] == other && board[i][0] != mark && board[i][2] != mark) {
                        score--;
                    }
                } else if (j == 2) {
                    if (board[i][2] == other && board[i][1] != mark && board[i][0] != mark) {
                        score--;
                    }
                }
                if (i == 0) {
                    if (board[0][i] == other && board[1][i] != mark && board[2][i] != mark) {
                        score--;
                    }
                } else if (i == 1) {
                    if (board[1][j] == other && board[0][j] != mark && board[2][j] != mark) {
                        score--;
                    }
                } else if (i == 2) {
                    if (board[2][j] == other && board[1][j] != mark && board[0][j] != mark) {
                        score--;
                    }
                }
            }
        }
        //player
        if (board[0][0] == mark && board[2][2] == mark && board[1][1] != other) {
            score++;
        } else if(board[0][0] == mark && board[2][2] != other && board[1][1] == mark) {
            score++;
        } else if (board[0][0] != other && board[2][2] == mark && board[1][1] == mark) {
            score++;
        } else if (board[0][2] == mark && board[2][0] == mark && board[1][1] != other) {
            score++;
        } else if (board[0][2] == mark && board[2][0] != other && board[1][1] == mark) {
            score++;
        } else if (board[0][2] != other && board[2][0] == mark && board[1][1] == mark) {
            score++;
        }
        //opponent
        if (board[0][0] == other && board[2][2] == other && board[1][1] != mark) {
            score--;
        } else if (board[0][0] == other && board[2][2] != mark && board[1][1] == other) {
            score--;
        } else if (board[0][0] != mark && board[2][2] == other && board[1][1] == other) {
            score--;
        } else if (board[0][2] == other && board[2][0] == other && board[1][1] != mark) {
            score--;
        } else if (board[0][2] == other && board[2][0] != mark && board[1][1] == other) {
            score--;
        } else if (board[0][2] != mark && board[2][0] == other && board[1][1] == other) {
            score--;
        }
        return score;
    }

    //xOrO
    //Helper function for returning the other character depending on our mark
    private static char xOrO(char mark){
        char other;
        if (mark == 'X') {
            other = 'O';
        } else {
            other = 'X';
        }
        return other;
    }
}
