package com.example.log;

import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PreProcess{

    public String removeDigits(String line){
        String result = line.replaceAll("\\(\\d+\\)", "");
        result = result.replaceAll("\\[\\d+\\]", "");
        return result;
    }
    
    public void processLines(String fileName) throws IOException {
        //read log file and write
        try{
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
    public JSONObject readJson() {
        JSONObject object = new JSONObject();;
        JSONParser jsonParser = new JSONParser();
        try{
            FileReader reader = new FileReader("errors.json");
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
                    System.out.println("found :"+eLine+"--------"+value);
                }
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred while reading.");
        }
    }
}
