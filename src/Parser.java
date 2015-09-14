import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Prabhjot on 9/12/2015.
 *
 *  Goal: A Parser accepts a CSV inputFile, parses it to create a list of Products and then returns the list.
 */
public class Parser {

    public LinkedList <Product> parseFile(String inputFile){
        String line;
        LinkedList <Product> list = new LinkedList();

        try {
            FileReader fileReader = new FileReader("./resources/"+inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Ignore first line of file.
            bufferedReader.readLine();

            // Parse the file, one line at a time.
            while ((line = bufferedReader.readLine()) != null) {
                // Parse the current line, tokening it by using commas as delimiters.
                String token[] = line.split(",");

                Product product = new Product(Integer.parseInt(token[0]), token[1], token[2], Float.parseFloat(token[3]),
                       Integer.parseInt(token[4]), Float.parseFloat(token[5]), Boolean.parseBoolean(token[6]), token[7]);

                list.add(product);
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File not found: " + inputFile);
        }
        catch(IOException ex){
            System.out.println("Error reading file: " + inputFile);
        }
        return list;
    }
}
