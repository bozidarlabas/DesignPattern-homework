package com.blabas.uzdiz;


import com.blabas.uzdiz.registry.Registry;
import com.blabas.uzdiz.registry.RegistryBuilder;
import com.blabas.uzdiz.singleton.Command;

/**
 * Created by bozidar on 02.12.2015..
 */

public class App {
    public static void main(String[] args) {
        Registry registry = RegistryBuilder.getRegistry(args);

        Command command = Command.getInstance();
        command.setRegistry(registry);
        command.initialize();
    }
}
