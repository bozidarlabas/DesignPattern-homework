package com.blabas.uzdiz.observer;

/**
 * Created by bozidar on 04.12.2015..
 */
public abstract class Observer {
    protected Menu subject;
    public abstract void performFirstOperation();
    public abstract void performSecondOperation();
    public abstract void performThirdOperation(String elementCode, boolean status); //true - visible, false - invisible
    public abstract void performFourthOperation();
    public abstract void performFifthOperation(String fileName);
    public abstract void performSixthOperation();
    public abstract void performTestOperation();
}