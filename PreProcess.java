import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Set;

public class PreProcess{
    
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
                //int index = line.indexOf("]");
                line = line.substring(25);
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
}