package com.blabas.uzdiz.composite.component;

import com.blabas.uzdiz.factory.product.Shape;

import java.util.Iterator;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;
import static com.blabas.uzdiz.utils.SysoutWrapper.printlnHeader;

/**
 * Created by bozidar on 02.12.2015..
 */
public abstract class ElementComponent {

    public void add(ElementComponent element) {
        throw new UnsupportedOperationException();
    }

    public void remove(ElementComponent element) {
        throw new UnsupportedOperationException();
    }

    public ElementComponent getComponent(int index) {
        throw new UnsupportedOperationException();
    }

    public void displayElementInfo() {
        throw new UnsupportedOperationException();
    }

    public void displayChildInfo() {
        throw new UnsupportedOperationException();
    }

    public int getType() {
        throw new UnsupportedOperationException();
    }

    public void setType(int type) {
        throw new UnsupportedOperationException();
    }

    public String getCode() {
        throw new UnsupportedOperationException();
    }

    public void setCode(String code) {
        throw new UnsupportedOperationException();
    }

    public String getParrent() {
        throw new UnsupportedOperationException();
    }

    public void setParrent(String parrent) {
        throw new UnsupportedOperationException();
    }

    public String getColor() {
        throw new UnsupportedOperationException();
    }

    public void setColor(String color) {
        throw new UnsupportedOperationException();
    }

    public Shape getShape() {
        throw new UnsupportedOperationException();
    }

    public void setShape(Shape shape) {
        throw new UnsupportedOperationException();
    }

    public boolean isIntersectParrent() {
        throw new UnsupportedOperationException();
    }

    public void setIntersectParrent(boolean intersectParrent) {
        throw new UnsupportedOperationException();
    }

    public boolean isVisible() {
        throw new UnsupportedOperationException();
    }

    public void setVisible(boolean visible) {
        throw new UnsupportedOperationException();
    }

    public void displayVisibleIntersectedParrentInfo(){
        throw new UnsupportedOperationException();
    }

    public void displayVisibleIntersectedChildInfo(){
        throw new UnsupportedOperationException();
    }

    public void changeStatus(String code, boolean status){
        throw new UnsupportedOperationException();
    }

    public void changeChildStatus(String code, boolean status){
        throw new UnsupportedOperationException();
    }


    public void storeCode(){
        throw new UnsupportedOperationException();
    }

    public void storeCodeChild(){
        throw new UnsupportedOperationException();
    }


}
