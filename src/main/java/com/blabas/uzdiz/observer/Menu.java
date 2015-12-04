package com.blabas.uzdiz.observer;

import com.blabas.uzdiz.listener.OnMenuItemSelected;
import com.blabas.uzdiz.singleton.Command;

import java.util.List;
import java.util.Scanner;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 03.12.2015..
 */
public class Menu implements Subject{
    private Scanner sc;
    private Observer observer;
    private int state;

    public Menu() {
        sc = new Scanner(System.in);
    }

    public void choseOption() {
        int chosedOption = 0;
        while (chosedOption == 0 || chosedOption != 7) {
            println("\n========================================================");
            println("|                      MENU                            |");
            println("========================================================");
            println("| Opcije:                                              |");
            println("|        1. Pregled stanja                             |");
            println("|        2. Pregled jednostavnih elem. u presjeku      |");
            println("|        3. Promjena statusa elementa                  |");
            println("|        4. Ukupne povrsine boja                       |");
            println("|        5. Ucitavanje dodatne datoteke                |");
            println("|        6. Dohvacanje zadnjih promjena                |");
            println("|        7. Izlaz                                      |");
            println("========================================================");
            System.out.print("Odaberite opciju : ");

            chosedOption = Integer.parseInt(sc.nextLine());
            setState(chosedOption);
        }
    }


    @Override
    public void setState(int state) {
        this.state = state;
        notifyObserver(state);
    }

    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    public void notifyObserver(int chosedOption){
        switch (chosedOption) {
            case 1:
                observer.performFirstOperation();
                break;
            case 2:
                observer.performSecondOperation();
                break;
            case 3:
                System.out.print("Unesite sifru elementa : ");
                String code = sc.nextLine();
                System.out.print("Unesite novi status elementa : ");
                String status = sc.nextLine();
                if (status.equals("aktivan")) {
                    observer.performThirdOperation(code, true);
                } else {
                    observer.performThirdOperation(code, false);
                }
                break;
            case 4:
                observer.performFourthOperation();
                break;
            case 5:
                System.out.print("Unesite naziv datoteke : ");
                String fileName = sc.nextLine();
                observer.performFifthOperation(fileName);
                break;
            case 6:
                observer.performSixthOperation();
                break;
            case 8:
                observer.performTestOperation();
                break;
        }
    }
}
