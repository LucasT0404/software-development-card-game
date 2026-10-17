import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.io.ArrayIndexOutOfBoundsException;
import java.util.Collections;


public class Pack{
    private List<Int> pack;
    private int packIncrementer;

    public Pack(String filename, int numPlayers){
        pack = new ArrayList<>();
        packIncrementer = 0;
        
        //reading file contents line-by-line
        //storing in List 'pack'
        try (BufferedReader reader = new BufferedReader(new FileReader("filename.txt"))) {
            String line;
            //while line not empty
            while ((line = reader.readLine()) != null) {
                int tryInt = Integer.parseInt(line);
                if (tryInt < 0){
                    throw new IOException();
                }
                pack.add(Card(tryInt));
            }
        } catch (IOException e) {                   
            System.err.println("Error: invalid file.");
        } catch (NumberFormatException e){
            System.err.println("Line is not integer.");
        }

        //check if list is invalid (ie. correct length should be same as total number of cards)
        if (pack.size() != numCards){
            throw new IOException("Invalid Pack. Must be same size as total number of cards");
        }
        

        //shuffle cards, ie. Randomise
        Collections.shuffle(pack);

    }

    public int size(){
        return pack.size();
    }

    //counter to loop through list- increments by 1 each time
    public Card getCardFromPack(){
        if (packIncrementer >= size){
            throw new ArrayIndexOutOfBoundsException("Couldn't retrieve card from pack: end of pack exceeded.");
        } 
        
        return pack[packIncrementer++];
    }


        

    // How do we get the cards when CardGame wants another card?

}

