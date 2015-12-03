package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.composite.component.ElementComponent;

import java.util.ArrayList;
import java.util.List;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Validator {

    private boolean firstElement = true;

    public Validator(){}

    public void validateElement(List<ElementComponent> elements){
        if(isFirstElement()){
            for(ElementComponent element : elements){
                if(!checkFirstElementContainer(element)){
                    elements.remove(element);
                }
            }
        }
    }

    private boolean checkFirstElementContainer(ElementComponent element){
        if(element.getType() == 0){
            return true;
        }else{
            println("Element sa sifrom: " +element.getCode() + " je prvi element i nije kontejner");
            return false;
        }
    }

    public boolean codeAlreadyExist(ArrayList<ElementAdapter.Code> storedCodes, String componentCode){
       for(ElementAdapter.Code code : storedCodes){
           if(code.getStoredCode().equals(componentCode)){
               println("NEISPRAVAN ZAPIS: " + "element sa sifrom " + componentCode + " vec postoji!");
               return true;
           }

       }
        return false;
    }

    public void parrentCodeExistsMessage(String parrentCode, String childCode){
        println("NEISPRAVAN ZAPIS: " + "roditelj sa sifrom " + parrentCode + " ne postoji! (jednostavni element " + childCode + " ne moze biti sadrzan u njemu)");

    }



    public boolean isFirstElement() {
        return firstElement;
    }

    public void setFirstElement(boolean firstElement) {
        this.firstElement = firstElement;
    }
}
