import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CardGame {

    public static void main(String[] args) {
        private final List<Int> players  = new ArrayList<>();

        //reading in the number n of players in the game
        BufferedReader numReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter number of players (must be above-zero integer): ");
        int numPlayers = Integer.parseInt(readLine());
        

        //1) reading in the filename containing Pack cards
        BufferedReader filenameReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter filename: ");
        String filename = filenameReader.readLine();

        //adding n number of players to players ArrayList
        //starting each individual player's thread
        for (int i=0; i < numPlayers; i++){
            players[i].add(new Player());
            cardDecks[i] = new CardDeck();
        }


        //Distribute Hands To Players:
        int packIncrementer = 0;    //counter to loop through pack (increments each time after use) --> ensures same card in pack is not dealt twice.
        //for 4 times:
        for (int round = 0; round < 4; round++){
            //add one card to each player's hand:
            for (int p = 0; p < numPlayers; p++){
                Card cardToAddToHand = pack[packIncrementer++];
                players[p].receiveCard(cardToAddToHand);      
            }
        }

        //fill decks (round robin remainder of cards after hands) 
        for (int x = pack.size()/2; x < pack.size(); x += numPlayers){
            for (int d = 0; d < numPlayers; d++){
                Card cardToAddToDeck = pack[x];
                cardDecks[d].addCard(cardToAddToDeck);
            }
        }


        //start required threads after cards have been dealt.
        for (int y = 0; y < numPlayers; y++){
            new Thread(players[y]).start();
        }
    }

  



}