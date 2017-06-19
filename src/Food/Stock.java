package Food;

/**
 * Created by Dung Dinh on 5/31/2017.
 */
public class Stock {

    private static double grassQuantity = 1000;
    private static double meatQuantity = 500;
    private static double generalQuantity = 700;
    private static double riceQuantity = 800;

    public double getGrassQuantity() {
        return grassQuantity;
    }

    public void setGrassQuantity(double grassQuantity) {
        Stock.grassQuantity = grassQuantity;
    }

    public double getMeatQuantity() {
        return meatQuantity;
    }

    public void setMeatQuantity(double meetQuantity) {
        Stock.meatQuantity = meetQuantity;
    }

    public double getGeneralQuantity() {
        return generalQuantity;
    }

    public void setGeneralQuantity(double generalQuantity) {
        Stock.generalQuantity = generalQuantity;
    }

    public double getRiceQuantity() {
        return riceQuantity;
    }

    public void setRiceQuantity(double riceQuantity) {
        Stock.riceQuantity = riceQuantity;
    }
}
