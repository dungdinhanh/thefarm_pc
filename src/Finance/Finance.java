package Finance;

/**
 * Created by Dung Dinh on 5/25/2017.
 */
public class Finance {
    private double Balance;
    private int transactionID;
    private String type;
    private String action;
    private double amount;
    private String date;
    private String note;


    public void setNote(String note){
        this.note = note;
    }

    public String getNote(){
        return note;
    }

    public void setBalance(double Balance){
        this.Balance = Balance;
    }

    public double getBalance(){
        return Balance;
    }


    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

