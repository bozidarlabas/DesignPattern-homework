package com.blabas.uzdiz.composite.component;

import com.blabas.uzdiz.factory.product.Shape;
import com.blabas.uzdiz.iterator.ElementManager;
import com.blabas.uzdiz.iterator.Iterator;
import com.blabas.uzdiz.registry.Registry;
import com.blabas.uzdiz.singleton.Command;

import java.util.ArrayList;

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
    private Registry registry;


    private ArrayList<ElementComponent> elementComponents = new ArrayList<>();
    private ElementManager elementManager;

    public Element() {
        elementManager = Command.getInstance().getRegistry().provideElementManager();
    }

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

    public void add(ElementComponent element) {
        elementComponents.add(element);
    }

    public void remove(ElementComponent element) {
        elementComponents.remove(element);
    }

    public ElementComponent getComponent(int index) {
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

    public void displayElementInfo() {
        printlnHeader("\nSlozeni element: " + getCode() + " status: " + isVisible());

        Iterator elementIterator = elementManager.getIterator(elementComponents);

        while (elementIterator.hasNext()) {
            ElementComponent component = (ElementComponent) elementIterator.next();
            component.displayChildInfo();
        }
    }

    public void displayChildInfo() {
        println("   Jednostavni element: " + getCode() + " status: " + isVisible());

        Iterator elementIterator = elementManager.getIterator(elementComponents);

        while (elementIterator.hasNext()) {
            ElementComponent component = (ElementComponent) elementIterator.next();
            component.displayElementInfo();
        }
    }

    public void displayVisibleIntersectedParrentInfo() {
        boolean showParrent = false;
        Iterator elementIterator = elementManager.getIterator(elementComponents);
        //Iterator elementIterator = elementComponents.iterator();
        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            if (component.isIntersectParrent() && component.isVisible()) {
                if (!showParrent) {
                    printlnHeader("\nSlozeni element: " + getCode());
                    showParrent = true;
                }

                component.displayVisibleIntersectedChildInfo();
            }

        }
    }

    public void displayVisibleIntersectedChildInfo() {
        println("   Jednostavni element: " + getCode());

        Iterator elementIterator = elementManager.getIterator(elementComponents);

        while (elementIterator.hasNext()) {
            ElementComponent component = (ElementComponent) elementIterator.next();
            component.displayVisibleIntersectedParrentInfo();
        }
    }

    public void changeStatus(String code, boolean status) {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        //Iterator elementIterator = elementComponents.iterator();

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            if (component.getCode().equals(code)) {
                component.setVisible(status);
                break;
            } else {
                component.changeChildStatus(code, status);
            }


        }
    }

    public void changeChildStatus(String code, boolean status) {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            if (component.getCode().equals(code)) {
                component.setCode(code);
                break;
            } else {
                component.changeStatus(code, status);
            }

        }
    }

    public void storeCode() {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            elementManager.setAllStoredCodes(component.getCode());
            component.storeCodeChild();
        }
    }

    public void storeCodeChild() {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            elementManager.setAllStoredCodes(component.getCode());
            component.storeCode();

        }
    }

}
