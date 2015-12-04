package com.blabas.uzdiz.memento;

/**
 * Created by bozidar on 04.12.2015..
 */
public class Memento {
    private String elementCode;
    private boolean elementStatus;

    public Memento(String elementCode, boolean elementStatus){
        this.elementCode = elementCode;
        this.elementStatus = elementStatus;
    }

    public String getElementCode() {
        return elementCode;
    }

    public boolean getElementStatuts() {
        return elementStatus;
    }
}
