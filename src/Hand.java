import java.util.ArrayList;

public class Hand{
    private List<Int> hand = new ArrayList<>();

    //checks if player given all 4 cards of same value
    public Hand(hand){
        int num_same_cards = 0;
        for (int i = 0; i < hand.size(); i++ ):{
            if i < i+1{
                if hand.get(i) == hand.get(i+1){
                    num_same_cards +=;
                }
            }
        }
        //
        if num_same_cards == 4{
            //player = Player.playerIndex;
            System.out.println("Player " + player + "wins");
        }


    }


}