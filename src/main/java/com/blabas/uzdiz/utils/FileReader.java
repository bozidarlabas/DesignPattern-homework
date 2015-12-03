package com.blabas.uzdiz.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;
import static com.blabas.uzdiz.utils.SysoutWrapper.printlnHeader;


/**
 * Created by bozidar on 02.12.2015..
 */
public class FileReader {

    private ArrayList<String> parsedData;

    public FileReader(){
        parsedData = new ArrayList<>();
    }

    public void readAndParseTxtFile(String fileName){
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                parseLine(line);
            }
            printlnHeader("Datoteka: " + fileName + " je ucitana");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void parseLine(String line) {
        parsedData.add(line);
    }

    public ArrayList<String> getParsedData() {
        return parsedData;
    }
}
