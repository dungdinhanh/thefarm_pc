package Food;

/**
 * Created by Dung Dinh on 5/31/2017.
 */
public class Food {
    private int foodID;
    private String foodName;
    private double quantity;

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName() {
        if(foodID == 1) this.foodName = "Rice";
        if(foodID == 2) this.foodName = "Grass";
        if(foodID == 3) this.foodName = "General";
        if(foodID == 4) this.foodName = "Meat";
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
