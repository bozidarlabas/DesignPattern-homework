package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.listener.OnMenuItemSelected;
import com.blabas.uzdiz.singleton.Command;

import java.util.Scanner;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 03.12.2015..
 */
public class Menu {
    private Scanner sc;
    private OnMenuItemSelected onMenuItemSelected;

    public Menu(){
        sc = new Scanner(System.in);
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

    public void setOnMenuClickListener(OnMenuItemSelected onMenuItemSelected){
        this.onMenuItemSelected = onMenuItemSelected;
    }



    private void performOperation(int chosedOption){
        switch (chosedOption){
            case 1:
                break;
            case 2:
                onMenuItemSelected.performSecondOperation();
                break;
            case 3:
                System.out.print("Unesite sifru elementa : ");
                String code = sc.nextLine();
                System.out.print("Unesite novi status elementa : ");
                String status = sc.nextLine();
                if(status.equals("aktivni")){
                    onMenuItemSelected.performThirdOperation(code, true);
                }else{
                    onMenuItemSelected.performThirdOperation(code, false);
                }

                break;
        }
    }

}
