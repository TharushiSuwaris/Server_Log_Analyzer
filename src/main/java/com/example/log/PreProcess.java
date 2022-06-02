package com.example.log;

import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PreProcess{
    String text = "";
    String errors ="";
    String warnings ="";

    public String removeDigits(String line){
        String result = line.replaceAll("\\(\\d+\\)", "");
        result = result.replaceAll("\\[\\d+\\]", "");
        return result;
    }
    
    public void processLines(String fileName) throws IOException {
        //read log file and write
        try{
            File file = new File("newFile.txt");
            file.delete();
            File logFile = new File(fileName);
            Scanner reader = new Scanner(logFile);
            FileWriter writer = new FileWriter("newFile.txt", true);
            BufferedWriter out = new BufferedWriter(writer);
            Set set = new HashSet();
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                line = line.substring(25);
                line = removeDigits(line);
                if(set.add(line)){
                    out.write(line);
                    out.newLine();
                }
                
            }
            out.close();
            System.out.println("successfully written");
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred while reading.");
        }
    }
    public JSONObject readJson(String fileName) {
        JSONObject object = new JSONObject();;
        JSONParser jsonParser = new JSONParser();
        try{
            FileReader reader = new FileReader(fileName);
            object = (JSONObject) jsonParser.parse(reader);
        }catch(FileNotFoundException e){
            System.out.println("An error occurred while reading.");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return object;
    }
    
    public void checker(String fileName, String eLine, String value) {
        
        try{
            File logFile = new File(fileName);
            Scanner reader = new Scanner(logFile);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                if(line.matches(eLine)){
                    text = text.concat(line+"===>>"+value+"\n");
                    //System.out.println(text);
                    //System.out.println("found :"+line+"--------> "+value);
                }
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred while reading.");
        }
    }

    public void readJsonObject(String file) {
        if(file == "errors.json"){
            JSONObject array =  readJson(file);
            for (Object keyStr : array.keySet()) {
                Object keyvalue = array.get(keyStr);
                String key = keyStr.toString();
                String keyv = keyvalue.toString();
                checker("newFile.txt", key, keyv);
            }
            errors = text;
            text = "";
        }else{
            JSONObject array =  readJson(file);
            for (Object keyStr : array.keySet()) {
                Object keyvalue = array.get(keyStr);
                String key = keyStr.toString();
                String keyv = keyvalue.toString();
                checker("newFile.txt", key, keyv);
            }
            warnings = text;
            text = "";
        }
        
    }
}
