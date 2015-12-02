package com.blabas.uzdiz.composite.component;

import com.blabas.uzdiz.composite.component.ElementComponent;
import com.blabas.uzdiz.factory.product.Shape;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementLeaf extends ElementComponent {
    private int type;
    private String code;
    private String parrent;
    private String color;
    private Shape shape;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParrent() {
        return parrent;
    }

    public void setParrent(String parrent) {
        this.parrent = parrent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void displayElementInfo() {
        println("INFO::TEST: " + getType());
    }
}
