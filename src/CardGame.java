//initialise player, hand, deck etc
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CardGame {


    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter number of players (must be above-zero integer): ");
    int n = Integer.parseInt(reader.readLine);
    //each player holds hand of 4 cards -> total of n*4 cards
    int n = n * 4;


    public static void initialise(){



        for (int i=0; i < (4*n); i++){
            players[i] = new Player();
            players[i].start();
            
            //assign deck number and hand to player:

        }


    }



}