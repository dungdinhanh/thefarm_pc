package logic;

import java.util.HashMap;

/**
 * Created by Dung Dinh on 5/13/2017.
 */
public class Var {
    private static String userName;
    public static final String LINK = "http://thefarmteam.esy.es/process.php";
    public static final String KEY_METHOD = "method";
    public static final int METHOD_LOG_IN = 1;
    public static final int METHOD_SIGN_UP = 2;
    public static final int METHOD_ANIMAL = 3;
    public static final int METHOD_ADD = 5;
    public static final int METHOD_DELETE = 4;
    public static final int METHOD_ADD_LIST = 8;
    public static final int METHOD_SELL = 10;
    public static final int METHOD_BALANCE = 9;
    public static final int METHOD_ADD_MONEY = 6;
    public static final int METHOD_WITHDRAW = 7;
    public static final int METHOD_GET_TRANSACTION = 11;
    public static final int METHOD_GET_MEDICAL_ANIMALS = 12;
    public static final int METHOD_GET_SICK_ANIMALS = 13;
    public static final int METHOD_BUY_FOOD = 16; //14 is only for android
    public static final int METHOD_GET_FOOD = 15;
    public static final int METHOD_OUT_FOOD = 17;
    public static final int METHOD_SET_SICK = 18;
    public static final int METHOD_GET_MANIMALS = 19;



    public static final String KEY_USER = "Account";
    public static final String KEY_AMOUNT = "Amount";
    public static final String KEY_BALANCE = "Balance";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_ANIMAL_ID = "Animal_ID";
    public static final String KEY_CATTLE_ID = "Id";
    public static final String KEY_HEALTH_INDEX = "Health_Index";
    public static final String KEY_SOURCE = "Source";
    public static final String KEY_WEIGHT = "Weight";
    public static final String KEY_DATE = "Date_Import";
    public static final String KEY_SEX = "Sex";
    public static final String KEY_NUMBER = "Number_Animals";
    public static final String KEY_ARRAY_ID = "array_id";
    public static final String KEY_ANIMAL_INFO = "Animal_Info";
    public static final String KEY_FOOD_LIST = "Food_List";



    public static final String KEY_FOOD_ID = "Food_ID";
    public static final String KEY_FOOD_AMOUNT = "Food_Amount";

    public static final int FOOD_RICE  = 1;
    public static final int FOOD_GRASS = 2;
    public static final int FOOD_GENERAL = 3;
    public static final int FOOD_MEAT = 4;





    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }
}
