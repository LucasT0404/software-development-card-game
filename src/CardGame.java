import java.io.BufferedReader;
import java.util.Random;
import java.io.InputStreamReader;

public class CardGame {
    private int numPlayers;
    public static void main(String[] args) {
        

        //reading in the number n of players in the game
        BufferedReader numReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter number of players (must be above-zero integer): ");
        int numPlayers = Integer.parseInt(numReader.readLine);

        //1) reading in the filename containing Pack cards
        BufferedReader filenameReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter filename: ");
        String filename = filenameReader.readLine();

        //2) distribute hands to players:
        //player cannot have same card- using boolean value 'assigned' to be true if already added to a player's pack.

        boolean assigned = false;
        //for each player:
        for (int p =0; p < players.size(); p++){
            for (int n = 0; n < 4; n++){
                //get random card from pack and add to player's hand:
                players[p].getHand().addCard(...);
            //add 4 random cards from pack to each player's deck
            }
        }

        //3) fill decks, start required threads



        //total number of cards is number of players * 8 
        numCards = numPlayers * 8;




    }




    public static void initialise(){

        //adding n number of players to players ArrayList
        //starting each individual player's thread
        for (int i=0; i < numPlayers; i++){
            players[i] = new Player();
            players[i].start();

            deckCards[i] = new CardDeck();
            
            //assign deck number and hand to player - DO IN HAND CLASS?

        }


    }



}