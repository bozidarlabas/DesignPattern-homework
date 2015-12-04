package com.blabas.uzdiz.memento;

/**
 * Created by bozidar on 04.12.2015..
 */
public class ElementOriginator {
    private String elementCode;
    private String elementStatus;

    public Memento saveStateToMemento(){
        return new Memento(elementCode, elementStatus);
    }

    public void restoreFromMemento(Memento memento){
        this.elementCode = memento.getElementCode();
        this.elementStatus = memento.getElementStatuts();
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getElementStatus() {
        return elementStatus;
    }

    public void setElementStatus(String elementState) {
        this.elementStatus = elementState;
    }





}
