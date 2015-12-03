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

    public boolean checkRegex(String fileName){
        String sintaksa = "(.*\\.txt$)";
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher matcher = pattern.matcher(fileName);
        boolean status = matcher.matches();
        if(status){
            loadedFileName =  matcher.group(1).split(" ")[0];
            printlnHeader("Validacija 1 - naziv txt datoteke je ispravan");
            return true;
        }else{
            printlnHeader("Validacija 1 - naziv txt datoteke je neispravan");
        }

        return false;
    }

    public String getLoadedFileName() {
        return loadedFileName;
    }
}
