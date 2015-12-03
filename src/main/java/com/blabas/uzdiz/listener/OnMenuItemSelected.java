package com.blabas.uzdiz.listener;

/**
 * Created by bozidar on 03.12.2015..
 */
public interface OnMenuItemSelected {
    public void performFirstOperation();
    public void performSecondOperation();
    public void performThirdOperation(String elementCode, boolean status); //true - visible, false - invisible
}
