import java.util.Arrays;

public class Board {
    private static final int BOARD_SIZE = 3;
    private static final char PLAYER1_TOKEN = 'O';
    private static final char PLAYER2_TOKEN = 'X';
    private static final char EMPTY = '.';


    private char[] board;

    public Board(){
        //creates an array of a length of nine and sets every position as empty
        board = new char[BOARD_SIZE*BOARD_SIZE];
        Arrays.fill(board,EMPTY);

    }

    //to have a variable board size would just need to fix this print object
    public void printBoard() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j=0; j<BOARD_SIZE; j++) {
                System.out.print(board[BOARD_SIZE*i+j]);
            }
            System.out.println();
        }
    }

    public void player1Move(int position) {
        board[position] = PLAYER1_TOKEN;
    }

    public void player2Move(int position) {
        board[position] = PLAYER2_TOKEN;
    }

    public void unMakeLastMove(int lastPlayed) {
        board[lastPlayed] = EMPTY;
    }

    public char getState(int position) {
        return board[position];
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public char getEmptyClassifier() {
        return EMPTY;
    }

    public boolean playerWins(int lastPlayed) {

        char token = board[lastPlayed];

        //get the column and row
        int column = lastPlayed % BOARD_SIZE;
        int row = (int)(lastPlayed/BOARD_SIZE);

        if(checkRows(row, token) == true) {
            return true;
        }
            
        if(checkColumns(column, token) == true) {
            return true;
        }
        
        if(BOARD_SIZE % 2 == 1) {
            if(checkDiagonalLR(token)){
                return true;
            }
                    
            if(checkDiagonalRL(token)){
                return true;
            }
        }

        return false;    
    }

    private boolean checkRows(int row, char token) {

        for(int i = 0; i < BOARD_SIZE; i++) {
            if(board[BOARD_SIZE*row + i] != token) {
                return false; 
            }
        }

        return true;
    }

    private boolean checkColumns(int column, char token) {

        for(int i = 0; i < BOARD_SIZE; i++) {
            if(board[BOARD_SIZE * i + column] != token) {
                return false; 
            }
        }

        return true;
    }

    //the top left to bottom right diagonal
    private boolean checkDiagonalLR(char token) {
        
        for(int i = 0; i<BOARD_SIZE; i++) {
            if (board[i*(BOARD_SIZE+1)] != token) {
                return false;
            }
        }

        return true;
    }

    //the top right to bottom left diagonal
    private boolean checkDiagonalRL(char token) {
        
        for(int i = 1; i<=BOARD_SIZE; i++) {
            if (board[i*(BOARD_SIZE-1)] != token) {
                return false;
            }
        }

        return true;
    }
}