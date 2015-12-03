package com.blabas.uzdiz.composite.component;

import com.blabas.uzdiz.factory.product.Shape;

import java.util.ArrayList;
import java.util.Iterator;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;
import static com.blabas.uzdiz.utils.SysoutWrapper.printlnHeader;


/**
 * Created by bozidar on 02.12.2015..
 */
public class Element extends ElementComponent {

    private int type;
    private String code;
    private String parrent;
    private String color;
    private Shape shape;
    private boolean intersectParrent;
    private boolean visible = true;

    private ArrayList<ElementComponent> elementComponents = new ArrayList<>();

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

    public void add(ElementComponent element){
        elementComponents.add(element);
    }

    public void remove(ElementComponent element){
        elementComponents.remove(element);
    }

    public ElementComponent getComponent(int index){
        return elementComponents.get(index);
    }

    public boolean isIntersectParrent() {
        return intersectParrent;
    }

    public void setIntersectParrent(boolean intersectParrent) {
        this.intersectParrent = intersectParrent;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void displayElementInfo(){
        printlnHeader("\nSlozeni element: " + getCode());

        Iterator elementIterator = elementComponents.iterator();

        while (elementIterator.hasNext()){
            ElementComponent component = (ElementComponent)elementIterator.next();
            component.displayChildInfo();
        }
    }

    public void displayChildInfo(){
        println("   Jednostavni element: " + getCode());

        Iterator elementIterator = elementComponents.iterator();

        while (elementIterator.hasNext()){
            ElementComponent component = (ElementComponent)elementIterator.next();
            component.displayElementInfo();
        }
    }

    public void displayVisibleIntersectedParrentInfo(){
        boolean showParrent = false;


        Iterator elementIterator = elementComponents.iterator();

        while (elementIterator.hasNext()){
            ElementComponent component = (ElementComponent)elementIterator.next();
            if(component.isIntersectParrent() && component.isVisible()){
                if(!showParrent){
                    printlnHeader("\nSlozeni element: " + getCode());
                    showParrent = true;
                }

                component.displayChildInfo();
            }

        }
    }

    public void displayVisibleIntersectedChildInfo(){
        println("   Jednostavni element: " + getCode());

        Iterator elementIterator = elementComponents.iterator();

        while (elementIterator.hasNext()){
            ElementComponent component = (ElementComponent)elementIterator.next();
            component.displayVisibleIntersectedParrentInfo();
        }
    }


}
