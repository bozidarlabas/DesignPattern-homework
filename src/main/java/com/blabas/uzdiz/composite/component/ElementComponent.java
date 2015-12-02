package com.blabas.uzdiz.composite.component;

import com.blabas.uzdiz.composite.component.impl.Element;
import com.blabas.uzdiz.factory.product.Shape;

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

    public Element getComponent(int index) {
        throw new UnsupportedOperationException();
    }

    public void displayElementInfo() {
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


}
