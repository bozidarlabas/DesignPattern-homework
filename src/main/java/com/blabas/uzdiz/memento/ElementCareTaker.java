package com.blabas.uzdiz.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bozidar on 04.12.2015..
 */
public class ElementCareTaker {
    private List<Memento> listMemento;

    public ElementCareTaker(){
        listMemento = new ArrayList<>();
    }

    public void add(Memento newState){
        listMemento.add(newState);
    }

    public Memento get(int index){
        return listMemento.get(index);
    }
}
