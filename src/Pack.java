import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;

public class Pack{
    private List<Int> pack;

    public Pack(String filename, int numPlayers){
        pack = new ArrayList<>();
        
        //reading file contents line-by-line
        //storing in List 'pack'
        try (BufferedReader br = new BufferedReader(new FileReader("filename.txt"))) {
            String line;
            //while line not empty
            while ((line = br.readLine()) != null) {
                pack.add(line);
            }
        } catch (IOException e) {                   //DOES CHECKING SIZE OF PACK GO IN HERE INSTEAD OF BELOW????
            System.out.println("Error reading file.");
        }

        //check if list is invalid (ie. correct length should be same as total number of cards)
        if (pack.size() != numCards){
            throw new IOException("Invalid Pack. Must be same size as total number of cards");
        }



    }

    public int size(){
        return pack.size();
    }
        

    // How do we get the cards when CardGame wants another card?

}

