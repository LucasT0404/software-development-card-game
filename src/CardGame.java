import java.io.BufferedReader;
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

        //adding n number of players to players ArrayList
        //starting each individual player's thread
        for (int i=0; i < numPlayers; i++){
            players[i] = new Player();
            players[i].start();

            cardDecks[i] = new CardDeck();
            
        }


        //Distribute Hands To Players:
        //for 4 times:
        for (int i = 0; i < 4; i++){
            //add one card to each player's hand:
            for (int p = 0; p < playersSize; p++){
                Card cardToAddToHand = pack.getCardFromPack();      
                players[p].getHand().addCard(cardToAddToHand);      
            }
        }

        //fill decks (round robin remainder of cards after hands) 
        for (int x = pack.size()/2; x < pack.size(); x += numPlayers){
            for (int d = 0; d < numPlayers; d++){
                Card cardToAddToDeck = pack.getCardFromPack();
                cardDecks[d].addCard(cardToAddToDeck);
            }
        }



        //start required threads


        //total number of cards is number of players * 8 
        //numCards = numPlayers * 8;
    }

  



}