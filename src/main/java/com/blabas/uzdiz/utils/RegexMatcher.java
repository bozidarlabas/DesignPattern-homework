package com.blabas.uzdiz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;
import static com.blabas.uzdiz.utils.SysoutWrapper.printlnHeader;

/**
 * Created by bozidar on 02.12.2015..
 */
public class RegexMatcher {

    private String loadedFileName;

    private String getFileName(String args[]){
        StringBuilder sb = new StringBuilder();

        for(String arg : args){
            sb.append(arg).append(" ");
        }
        return sb.toString().trim();
    }

    public boolean checkRegex(String args[]){
        String sintaksa = "(.*\\.txt$)";
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher matcher = pattern.matcher(getFileName(args));
        boolean status = matcher.matches();
        if(status){
            printlnHeader("naziv datoteke");
            loadedFileName =  matcher.group(1).split(" ")[0];
            println("datoteka: " + matcher.group(1).split(" ")[0]);
            return true;
        }
        println("Preko komandne linije morate proslijediti naziv txt datoteke!");
        return false;
    }

    public String getLoadedFileName() {
        return loadedFileName;
    }
}
