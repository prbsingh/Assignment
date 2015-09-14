/**
 * Created by Prabhjot on 9/12/2015.
 *
 */
public class Product {

    private int productId;
    private String manufacturer;
    private String productCode;
    private float purchaseCost;
    private int quantityOnHand;
    private float markUp;
    private boolean available;
    private String description;

    public Product(){};

    public Product(int productId, String manufacturer, String productCode, float purchaseCost, int quantityOnHand, float markUp, boolean available, String description) {
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.productCode = productCode;
        this.purchaseCost = purchaseCost;
        this.quantityOnHand = quantityOnHand;
        this.markUp = markUp;
        this.available = available;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(float purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public float getMarkUp() {
        return markUp;
    }

    public void setMarkUp(float markUp) {
        this.markUp = markUp;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String print(){
        String str = this.productId + "," + this.manufacturer +"," + this.productCode +"," + this.purchaseCost +"," + this.markUp +"," + this.available +"," + this.description;
        System.out.println(str);
        return str;
    }
}
