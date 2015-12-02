package com.blabas.uzdiz.singleton;

import com.blabas.uzdiz.utils.FileReader;
import com.blabas.uzdiz.utils.RegexMatcher;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Command {
    /**
     * Static to class instance of the class.
     */
    private static final Command instance = new Command();

    /**
     * Private constructor so nobody can instantiate the class.
     */
    private Command() {}

    /**
     * To be called by user to obtain instance of the class.
     *
     * @return instance of the singleton.
     */
    public static Command getInstance() {
        return instance;
    }

    /**
     * First called method in main() for initializing project
     * @param args passed argument from command line
     */
    public void initialize(String args[]){
        RegexMatcher regexMatcher = new RegexMatcher();
        if(regexMatcher.checkRegex(args)){
            FileReader fileReader = new FileReader();
            fileReader.readAndParseTxtFile(regexMatcher.getLoadedFileName());
            //println(fileReader.getParsedData().get(0));
        }
    }
}
