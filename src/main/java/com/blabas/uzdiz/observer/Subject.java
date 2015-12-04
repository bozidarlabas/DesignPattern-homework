package com.blabas.uzdiz.observer;

/**
 * Created by bozidar on 04.12.2015..
 */
public interface Subject {
    public void setState(int state);
    public void addObserver(Observer observer);
}