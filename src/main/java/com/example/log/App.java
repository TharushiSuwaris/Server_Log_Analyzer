package com.example.log;

import java.io.IOException;

//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.SftpException;

import org.json.simple.JSONObject;

public class App 
{
    public static void main(String[] args) throws IOException{
        PreProcess process = new PreProcess();
        process.processLines("mz.0");
        JSONObject array =  process.readJson();
        for (Object keyStr : array.keySet()) {
            Object keyvalue = array.get(keyStr);
            String key = keyStr.toString();
            String keyv = keyvalue.toString();
            process.checker("newFile.txt", key, keyv);
        }

       // readSFTP download = new readSFTP();
       // download.DownloadFile();
    }
}
