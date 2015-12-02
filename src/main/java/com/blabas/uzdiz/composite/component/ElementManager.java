package com.blabas.uzdiz.composite.component;

import java.util.List;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementManager {

    List<ElementComponent> items;

    public ElementManager(List<ElementComponent> items) {
        this.items = items;
    }

    public void getElementList() {
        for(ElementComponent component : items){
            component.displayElementInfo();
        }

    }
}
