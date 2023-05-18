import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String s1 = null;
        int choice = 0;

        while(choice!= 1 && choice!=2){
            while (true) {
                try {
                    
                    //try to execute the folowing lines
                    System.out.print("Press 1 to play against the AI or 2 to play with a friend: ");
                    s1 = reader.nextLine();
                    choice = Integer.parseInt(s1);
    
                    //If everything went fine, break the loop and move on.
                    break;
    
                } catch (NumberFormatException e) {
    
                    //If the method Integer.parseInt throws the exception, cathc and print the message.
                    System.out.println("Not a valid input, please try again.");
            
                }
            }
        }


        if(choice == 2) {
            Game game = new Game();
            game.play();
        }

        else if(choice == 1) {
            choice = 0;
    
            while(choice!= 1 && choice!=2){
                while (true) {
                    try {
                        
                        //try to execute the folowing lines
                        System.out.print("Press 1 to play as player 1 or 2 to play as player 2: ");
                        s1 = reader.nextLine();
                        choice = Integer.parseInt(s1);
        
                        //If everything went fine, break the loop and move on.
                        break;
        
                    } catch (NumberFormatException e) {
        
                        //If the method Integer.parseInt throws the exception, cathc and print the message.
                        System.out.println("Not a valid input, please try again.");
                
                    }
                }
            }
            GameAI game = new GameAI();
            game.play(choice);
        }
    }
}
