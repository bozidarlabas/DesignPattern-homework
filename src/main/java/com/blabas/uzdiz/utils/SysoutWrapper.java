package com.blabas.uzdiz.utils;

/**
 * Created by bozidar on 02.12.2015..
 */
public class SysoutWrapper {
    public static void println(String text){
        System.out.println(text);
    }

    public static void printlnHeader(String text){
        System.out.println(text.toUpperCase());
        for(int i = 0; i < text.length(); i++){
            System.out.print("-");
        }
        System.out.println("");
    }
}
