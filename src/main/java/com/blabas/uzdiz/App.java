package com.blabas.uzdiz;

import com.blabas.uzdiz.singleton.Command;
import com.blabas.uzdiz.utils.FileReader;
import com.blabas.uzdiz.utils.RegexMatcher;

import static com.blabas.uzdiz.utils.SysoutWrapper.*;

/**
 * Created by bozidar on 02.12.2015..
 */
public class App {
    public static void main(String[] args) {

        Command command = Command.getInstance();
        command.initialize(args);
    }
}
