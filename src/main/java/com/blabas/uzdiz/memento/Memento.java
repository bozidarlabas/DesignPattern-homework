package com.blabas.uzdiz.memento;

/**
 * Created by bozidar on 04.12.2015..
 */
public class Memento {
    private String elementCode;
    private String elementStatus;

    public Memento(String elementCode, String elementStatus){
        this.elementCode = elementCode;
        this.elementStatus = elementStatus;
    }

    public String getElementCode() {
        return elementCode;
    }

    public String getElementStatuts() {
        return elementStatus;
    }
}
