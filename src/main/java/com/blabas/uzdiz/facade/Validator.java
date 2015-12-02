package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.composite.component.ElementComponent;

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
            println("ElementLeaf sa sifrom: " +element.getCode() + " je prvi element i nije kontejner");
            return false;
        }
    }



    public boolean isFirstElement() {
        return firstElement;
    }

    public void setFirstElement(boolean firstElement) {
        this.firstElement = firstElement;
    }
}
