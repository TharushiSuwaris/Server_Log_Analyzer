package com.example.log;

import java.io.IOException;

import org.json.simple.JSONObject;

public class App 
{
    public static String extractErrors(JSONObject obj) {
        String key ="";
        for (Object keyStr : obj.keySet()) {
            //Object keyvalue = obj.get(keyStr);
            key = keyStr.toString();
            System.out.println(key);
            return key;
        }
        return key;
    } 
    public static void main(String[] args) throws IOException {
        PreProcess process = new PreProcess();
        process.processLines("mz.0");
        JSONObject array =  process.readJson();
        //String key = extractErrors(array);
        for (Object keyStr : array.keySet()) {
            Object keyvalue = array.get(keyStr);
            String key = keyStr.toString();
            String keyv = keyvalue.toString();
            process.checker("newFile.txt", key, keyv);
        }
    }
}
