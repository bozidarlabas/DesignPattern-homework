package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.singleton.Command;

import java.util.Scanner;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 03.12.2015..
 */
public class Menu {
    private Scanner sc;
    private Command command;

    public Menu(){
        sc = new Scanner(System.in);
        command = Command.getInstance();
    }

    public void choseOption(){
        int chosedOption = 0;
        while(chosedOption == 0 || chosedOption != 6){
            println("========================================================");
            println("|                      MENU                            |");
            println("========================================================");
            println("| Opcije:                                              |");
            println("|        1. Pregled stanja                             |");
            println("|        2. Pregled jednostavnih elem. u presjeku      |");
            println("|        3. Promjena statusa elementa                  |");
            println("|        4. Ukupne povrsine boja                       |");
            println("|        5. Vlastita funkcionalnost                    |");
            println("|        6. Izlaz                                      |");
            println("========================================================");
            System.out.print("Odaberite opciju : ");

            chosedOption = Integer.parseInt(sc.nextLine());
            performOperation(chosedOption);
        }
    }



    private void performOperation(int chosedOption){
        switch (chosedOption){
            case 1:
                break;
            case 2:
                command.performSecondOperation();
                break;
        }
    }

}
