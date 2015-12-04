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

    public Memento getLastState(){
        if(listMemento.size() > 0)
            return listMemento.get(listMemento.size() - 1);
        return new Memento("", false);
    }

    public Memento getBeforeLastState(){
        if(listMemento.size() > 1)
            return listMemento.get(listMemento.size() - 2);
        return new Memento("", false);
    }
}
