package com.example.log;

import java.io.IOException;

public class App 
{
    public static void main(String[] args) throws IOException {
        PreProcess process = new PreProcess();
        process.processLines("mz.0");
        process.readJson();
        /*try{
            File logFile = new File("ew.txt");
            Scanner reader = new Scanner(logFile);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                process.checker("newFile.txt",line );
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred while reading.");
        }*/
    }
}
