import java.util.Scanner;

public class Game {
    private int turn = 1;
    private int positionOfLastPlay = 0;
    private Board gameBoard;
    private Scanner reader = new Scanner(System.in);
    private final int UN_MAKE_MOVE = -1;
    private int turnsPlayed = 0;


    // creates the intitial board
    public Game() {
        gameBoard = new Board();
    }

    public void play(){

        // when done == true the game has ended
        boolean done = false;

        while(done == false && turnsPlayed < (gameBoard.getBoardSize()*gameBoard.getBoardSize())) {
            if (turn == 1) {
                this.Player1Turn();
            }
            else if (turn == 2){
                this.Player2Turn();
            }
        }

        gameBoard.printBoard();
        System.out.println("Draw: 1/2-1/2");
        System.exit(0);
    }

    private void Player1Turn() {

        //boolean valid = false;
        int position;
        gameBoard.printBoard();

        while(true){
            position = getInteger();

            if(position == UN_MAKE_MOVE && turnsPlayed != 0) {
                gameBoard.unMakeLastMove(positionOfLastPlay);
                turn = 2;
                turnsPlayed --;
                return;
            }

            else if(validMove(position) == true) {
                break;
            }

            System.out.println("Not a valid input, please try again.");
        }   
        
        gameBoard.player1Move(position);
        positionOfLastPlay = position;
        turn = 2;
        turnsPlayed ++;

        //checks if the game has been won
        if(gameBoard.playerWins(position) == true) {
            gameBoard.printBoard();
            System.out.println("Player 1 wins: 1-0");
            System.exit(0);
        }

        return;
    }

    private void Player2Turn() {

        //boolean valid = false;
        int position;
        gameBoard.printBoard();

        while(true){
            position = getInteger();

            if(position == UN_MAKE_MOVE) {
                gameBoard.unMakeLastMove(positionOfLastPlay);
                turn = 1;
                turnsPlayed --;
                return;
            }

            else if(validMove(position) == true) {
                break;
            }

            System.out.println("Not a valid input, please try again.");
        }   
        
        gameBoard.player2Move(position);
        positionOfLastPlay = position;
        turn = 1;
        turnsPlayed ++;

        if(gameBoard.playerWins(position) == true) {
            gameBoard.printBoard();
            System.out.println("Player 2 wins: 0-1");
            System.exit(0);
        }

        return;
    }


    private boolean validMove(int position) {
        //checks that it is within the limits of the board
        if (position>=0 && position < (gameBoard.getBoardSize()*gameBoard.getBoardSize()) ) {
            //check that it is an empty spot
            if (gameBoard.getState(position) == gameBoard.getEmptyClassifier()) {
                return true;
            }
        }

        return false;
    }


    private int getInteger(){
        String s1 = null;
        int value;

        while (true) {
            try {
    
                //try to execute the folowing lines
                System.out.print("Where do you want to play: ");
                s1 = reader.nextLine();
                value = Integer.parseInt(s1);
    
                //If everything went fine, break the loop and move on.
                break;
    
            } catch (NumberFormatException e) {
    
                //If the method Integer.parseInt throws the exception, cathc and print the message.
                System.out.println("Not a valid input, please try again.");
            }
        }

        return value;
    }
}