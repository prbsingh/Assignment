import sun.awt.image.ImageWatched;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Prabhjot on 9/13/2015.
 *
 * A DatabaseClient makes adding/retrieving products from the database easier by converting types to/from the database.
 */
public class DatabaseClient {

    private DataStore dataStore;

    public DatabaseClient(DataStore dataStore){
        this.dataStore = dataStore;
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addAllProducts(LinkedList products) {
        ListIterator<Product> itr = products.listIterator();
        while(itr.hasNext()){
            if (addProduct(itr.next()) != 1){
                System.out.println("addAllProducts: Error while adding ProductID " + itr.previous().getProductId());
            }
        }
    }

    public int addProduct(Product product) {
        StringBuilder query = new StringBuilder();

        query.append("insert into PRODUCTS VALUES (");

        query.append(Integer.toString(product.getProductId()));
        query.append(",");
        query.append("\'" + product.getManufacturer() + "\'");
        query.append(",");
        query.append("\'"+product.getProductCode()+"\'");
        query.append(",");
        query.append(Float.toString(product.getPurchaseCost()));
        query.append(",");
        query.append(Integer.toString(product.getQuantityOnHand()));
        query.append(",");
        query.append(Float.toString(product.getMarkUp()));
        query.append(",");
        query.append(Boolean.toString(product.isAvailable()));
        query.append(",");
        query.append("\'"+product.getDescription()+"\'");

        query.append(")");

        try {
            return this.dataStore.update(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void printProductList(){

        ResultSet rs = getAllProducts();
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("PRODUCT_ID"));
                product.setManufacturer(rs.getString("MANUFACTURER"));
                product.setProductCode(rs.getString("PRODUCT_CODE"));
                product.setPurchaseCost(rs.getFloat("PURCHASE_COST"));
                product.setQuantityOnHand(rs.getInt("QUANTITY_ON_HAND"));
                product.setMarkUp(rs.getFloat("MARKUP"));
                product.setAvailable(rs.getBoolean("AVAILABLE"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.print();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllProducts(){
        String query = "select * from PRODUCTS";
        try {
            return this.dataStore.query(query);
        } catch (SQLException e) {
            e.printStackTrace();
    }
    return null;
}

    public LinkedList<Product> convertToList(ResultSet rs){
        LinkedList<Product> list = new LinkedList<Product>();
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("PRODUCT_ID"));
                product.setManufacturer(rs.getString("MANUFACTURER"));
                product.setProductCode(rs.getString("PRODUCT_CODE"));
                product.setPurchaseCost(rs.getFloat("PURCHASE_COST"));
                product.setQuantityOnHand(rs.getInt("QUANTITY_ON_HAND"));
                product.setMarkUp(rs.getFloat("MARKUP"));
                product.setAvailable(rs.getBoolean("AVAILABLE"));
                product.setDescription(rs.getString("DESCRIPTION"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void getProductById(int id){
        String query = "select * from PRODUCTS where PRODUCT_ID = " + id;
        LinkedList<Product> list = new LinkedList<Product>();

        try {
             list = convertToList(this.dataStore.query(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Results matching ID " + id);
        for(Product p : list){

            p.print();
        }
    }

    public void getProductByDescription(String desc){
        String query = "select * from PRODUCTS where DESCRIPTION = \'" + desc + "\'";
        LinkedList<Product> list = new LinkedList<Product>();
        try {
            list = convertToList(this.dataStore.query(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Results matching Description " + desc);
        for(Product p : list){
            p.print();
        }
    }

}
