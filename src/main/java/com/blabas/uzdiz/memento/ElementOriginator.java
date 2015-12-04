package com.blabas.uzdiz.memento;

/**
 * Created by bozidar on 04.12.2015..
 */
public class ElementOriginator {
    private String elementCode;
    private boolean elementStatus;

    public Memento saveStateToMemento() {
        return new Memento(elementCode, elementStatus);
    }

    public void restoreFromMemento(Memento memento) {
        this.elementCode = memento.getElementCode();
        this.elementStatus = memento.getElementStatuts();
    }


    public void setState(String elementCode, boolean elementStatus) {
        this.elementCode = elementCode;
        this.elementStatus = elementStatus;
    }

    public String getElementCode() {
        return elementCode;
    }


    public boolean getElementStatus() {
        return elementStatus;
    }

}
