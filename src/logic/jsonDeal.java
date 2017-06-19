package logic;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dung Dinh on 5/13/2017.
 */
public class jsonDeal {

    public  <T> T loadJSON(int method, HashMap<String, String> request) throws Exception{
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost requestPost = new HttpPost(Var.LINK);
        requestPost.addHeader("content-type", "application/json");
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put(Var.KEY_METHOD, method);
        for( Map.Entry a : request.entrySet()){
                jsonRequest.put(a.getKey(), a.getValue());
        }
        System.out.println(jsonRequest);
        StringEntity stringRequest = new StringEntity(jsonRequest.toString());
        requestPost.setEntity(stringRequest);
        HttpResponse response = httpClient.execute(requestPost);

        String jsonStringResponse = EntityUtils.toString(response.getEntity());
        System.out.println(jsonStringResponse);
        Object jsonResponse =  new JSONParser().parse(jsonStringResponse);
        if(jsonResponse instanceof JSONObject) {
            JSONObject jo = (JSONObject) jsonResponse;
            return (T)jo;
        }
        else
        {
            JSONArray ja = (JSONArray) jsonResponse;
            return (T)ja;
        }
    }

}
