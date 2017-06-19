package Cattle;

import java.sql.Date;

/**
 * Created by Dung Dinh on 5/14/2017.
 */
public class Animal {

    private int animalID;
    private int cattleID;
    private int healthIndex;
    private String sex;
    private int sexCode;
    private String animal;
    private double weight;
    private String source;
    private String dateImport;

    private double temperature;


    public void setSexCode(int sexCode)
    {
        this.sexCode = sexCode;
    }

    public int getSexCode(){
        return sexCode;
    }


    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public int getCattleID() {
        return cattleID;
    }

    public int getHealthIndex() {
        return healthIndex;
    }

    public void setHealthIndex(int healthIndex) {
        this.healthIndex = healthIndex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex() {

        if(sexCode == 1) sex = "Male";
        else sex = "Female";
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDateImport() {
        return dateImport;
    }

    public void setDateImport(String dateImport) {
        this.dateImport = dateImport;
    }

    public void setCattleID(int cattle_ID) {
        this.cattleID = cattle_ID;
    }

    public void setTemperature(double temperature){
        this.temperature = temperature;
    }

    public double getTemperature(){
        return temperature;
    }
}
