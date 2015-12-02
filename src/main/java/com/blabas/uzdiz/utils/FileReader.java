package com.blabas.uzdiz.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;


/**
 * Created by bozidar on 02.12.2015..
 */
public class FileReader {

    public void readAndParseTxtFile(String fileName){
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                parseLine(line);
            }
            println("Datoteka: " + fileName + " je ucitana");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void parseLine(String line) {
        int index=0;
        index = line.indexOf("\t");

    }


}
