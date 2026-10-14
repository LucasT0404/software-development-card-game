import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//FileWriter: directly writes text to character files using default buffer (size NOT specified)
//BufferedWriter: wraps around FileWriter (

public class CardDeck {
    private int deckNumber;


    public cardDeck(int deckNumber){
        this.deckNumber = deckNumber;
    }

    public int getDeckNumber(){
        return deckNumber;
    }

    public Card drawCard(){
        Card topOfPack = this.hand[0];
    }






    //each deck's final contents written to deckX_output.txt file
    //each deck has a seperate file.
    public void writeToDeckFile(){
        String fileName = "deck" + deckNumber + "_output.txt";
        try (FileWriter writer = new FileWriter(fileName)){
            //writer.write(deckXcontents);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    

}