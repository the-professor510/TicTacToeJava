import java.util.Scanner;

public class GameAI {
    private int turn = 1;
    private int positionOfLastPlay = 0;
    private Board gameBoard;
    private Scanner reader = new Scanner(System.in);
    private final int UN_MAKE_MOVE = -1;
    private int turnsPlayed = 0;


    // creates the intitial board
    public GameAI() {
        gameBoard = new Board();
    }

    public void play(int choice){

        // when done == true the game has ended
        boolean done = false;

        if(choice == 1){
            while(done == false && turnsPlayed < (gameBoard.getBoardSize()*gameBoard.getBoardSize())) {
                if (turn == 1) {
                    this.PlayerTurn();
                }
                else if (turn == 2){
                    this.AI();
                }
            }
        }
        else if(choice == 2){
            this.AI();
            while(done == false && turnsPlayed < (gameBoard.getBoardSize()*gameBoard.getBoardSize())) {
                if (turn == 1) {
                    this.PlayerTurn();
                }
                else if (turn == 2){
                    this.AI();
                }
            }     
        }



        gameBoard.printBoard();
        System.out.println("Draw: 1/2-1/2");
        System.exit(0);
    }

    private void PlayerTurn() {

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

    private void AI() {
        //AI is to make its turn

        int bestScore = -50000;
        int move = 0;
        for (int i = 0; i < gameBoard.getBoardSize()*gameBoard.getBoardSize(); i++) {
            if (validMove(i)) {
                gameBoard.player2Move(i);
                int score = this.minimax(0, false, i);
                gameBoard.unMakeLastMove(i);

                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }

        gameBoard.player2Move(move);
        turnsPlayed ++;
        turn = 1;

        //checks if the game has been won
        if(gameBoard.playerWins(move) == true) {
            gameBoard.printBoard();
            System.out.println("AI wins: 1-0");
            System.exit(0);
        }

        return;
    }

    private int minimax(int depth, Boolean isMaximizing, int lastPlayed) {
        if (gameBoard.playerWins(lastPlayed)) {
            if (depth%2 == 0) {
                return 100;
            }
            else if(depth%2 == 1) {
                return -100;
            }
        }

        if ((turnsPlayed + depth + 1) == gameBoard.getBoardSize()*gameBoard.getBoardSize()) {
            return 0;
        }
          
        if (isMaximizing) {
            int bestScore = -50000;
            for (int i = 0; i < gameBoard.getBoardSize()*gameBoard.getBoardSize(); i++) {
                if (validMove(i)) {
                    gameBoard.player2Move(i);
                    int score = this.minimax(depth + 1, false, i);
                    gameBoard.unMakeLastMove(i);
    
                    if (score > bestScore) {
                        bestScore = score;
                    }
                }
            }
          return bestScore;
        }
        
        else {
            int bestScore = 50000;
            for (int i = 0; i < gameBoard.getBoardSize()*gameBoard.getBoardSize(); i++) {
                if (validMove(i)) {
                    gameBoard.player1Move(i);
                    int score = this.minimax(depth + 1, true, i);
                    gameBoard.unMakeLastMove(i);
    
                    if (score < bestScore) {
                        bestScore = score;
                    }
                }
            }
            return bestScore;
        }
    }
}