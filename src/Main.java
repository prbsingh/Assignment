import java.util.LinkedList;

/**
 * Created by Prabhjot on 9/12/2015.
 */
public class Main {
    public static void main (String[] args) throws Exception{

        String inputFile = "products.csv";

        Parser parser = new Parser();

        // Parse the CSV file specified and convert to a Linked-List of Products.
        LinkedList<Product> productList = parser.parseFile(inputFile);

        // Create a database for Products.
        DataStore productDataBase = new DataStore();

        // Create a DB to client to interact with Product database.
        DatabaseClient client = new DatabaseClient(productDataBase);

        // Add all products in memory to the database.
        client.addAllProducts(productList);

        // Print a list of all products from the database.
        client.printProductList();

        // Get product from database using Id.
        client.getProductById(980005);

        // Get product from database using Description.
        client.getProductByDescription("Printer Cable");

        // Clean-up
        productDataBase.close();
    }
}
