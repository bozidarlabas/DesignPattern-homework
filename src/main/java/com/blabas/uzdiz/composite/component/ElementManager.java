package com.blabas.uzdiz.composite.component;

import com.blabas.uzdiz.composite.component.ElementComponent;

import java.util.List;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementManager {


    List<ElementComponent> items;

    public void setItems(List<ElementComponent> items) {
        this.items = items;
    }

    public void getElementList() {
        for(ElementComponent component : items){
            component.displayElementInfo();
        }
    }

    public void displayVisibleIntersectedElements(){
        for(ElementComponent component : items){
            component.displayVisibleIntersectedParrentInfo();
        }
    }

    public void changeElementStatus(){

    }
}
