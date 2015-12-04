package com.blabas.uzdiz.composite.component;

import com.blabas.uzdiz.factory.product.Shape;
import com.blabas.uzdiz.iterator.ElementManager;
import com.blabas.uzdiz.iterator.Iterator;
import com.blabas.uzdiz.registry.Registry;
import com.blabas.uzdiz.singleton.Command;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;

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
    private String borderIntersectParrent;
    private boolean visible = true;
    private HashMap<String, String> intersectedChilds;


    private ArrayList<ElementComponent> elementComponents = new ArrayList<>();
    private ElementManager elementManager;

    public Element() {
        elementManager = Command.getInstance().getRegistry().provideElementManager();
        intersectedChilds = new HashMap<>();
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

    public String getBorderIntersectParrent() {
        return borderIntersectParrent;
    }

    public void setBorderIntersectParrent(String borderIntersectParrent) {
        this.borderIntersectParrent = borderIntersectParrent;
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

        for(int i = 0; i < elementComponents.size(); i++){
            ElementComponent component = elementComponents.get(i);
            for(int j = (i+1); j < elementComponents.size(); j++){

                ElementComponent component2 = elementComponents.get(j);
                if(component.isVisible() && component2.isVisible() && !component.getCode().equals(component2.getCode())){
                    if (!showParrent) {
                        printlnHeader("\nSlozeni element: " + getCode());
                        showParrent = true;
                    }
                    Area a = new Area(component.getShape().getRealShape());
                    Area b = new Area(component2.getShape().getRealShape());
                    b.intersect(a);
                    if (!b.isEmpty()) {
                        System.out.println("    Sifre parova jednostavnih elemenata u presjeku: 1. " + component.getCode() + ", 2. " + component2.getCode());
//                        intersectedChilds.put(component.getCode(), component2.getCode());
                        displayVisibleIntersectedChildInfo();
                    }
                }
            }
        }
    }

    public void displayVisibleIntersectedChildInfo() {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        while (elementIterator.hasNext()) {
            ElementComponent component = (ElementComponent) elementIterator.next();
            component.displayVisibleIntersectedParrentInfo();
        }
    }

    public void changeStatus(String code, boolean status) {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        //Iterator elementIterator = elementComponents.iterator();

        if (getCode().equals(code)) {
            setVisible(status);
        }

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            if (component.getCode().equals(code)) {
                component.setVisible(status);
            }
            component.changeChildStatus(code, status);


        }
    }

    public void changeChildStatus(String code, boolean status) {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        if (getCode().equals(code)) {
            setVisible(status);
        }

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            if (component.getCode().equals(code)) {
                component.setCode(code);
            }
            component.changeStatus(code, status);


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

    public void getElementColorSurface() {


        // println("RODITELJ: " + getCode() + " povrsina: " + getShape().getArea());

        if (isVisible()) {
            Iterator elementIterator = elementManager.getIterator(elementComponents);
            while (elementIterator.hasNext()) {
                ElementComponent component = elementIterator.next();
                if (component.isVisible()) {
                    component.getChildElementColorSurface();
                }
            }
        }
    }

    public void getChildElementColorSurface() {
        Iterator elementIterator = elementManager.getIterator(elementComponents);

        println("DiJETE: " + getCode() + " povrsina: " + getShape().getArea() + " boja: " + getColor());
        elementManager.setColors(getColor(), getShape().getArea());

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            if (component.isVisible()) {
                component.getElementColorSurface();
            }
        }
    }

    public void getElementsWithBorder() {

        System.out.print("\n\nRODITELJ | Sifra: " + getCode() + " | Koordinate: ");
        for(int coord : getShape().getCoordinates()){
            System.out.print(coord + ",");
        }
        System.out.print(" | Boja: " + getColor() + "\n");

        if (isVisible()) {
            Iterator elementIterator = elementManager.getIterator(elementComponents);
            while (elementIterator.hasNext()) {
                ElementComponent component = elementIterator.next();
                if (component.isVisible()) {
                    component.getChildParrentElementsWithBorder();
                }
            }
        }
    }

    public void getChildParrentElementsWithBorder() {
        Iterator elementIterator = elementManager.getIterator(elementComponents);


        System.out.print("\n    Dijete | Sifra: " + getCode() + " | Roditelj: " + getParrent() + " | Koordinate: ");
        for(int coord : getShape().getCoordinates()){
            System.out.print(coord + ",");
        }
        System.out.print(" | Boja: " + getColor());

        if(!getBorderIntersectParrent().equals("")){
            System.out.print(" | Prestup granice: " + getBorderIntersectParrent());
        }

        elementManager.setColors(getColor(), getShape().getArea());

        while (elementIterator.hasNext()) {
            ElementComponent component = elementIterator.next();
            if (component.isVisible()) {
                component.getElementsWithBorder();
            }

        }
    }

}