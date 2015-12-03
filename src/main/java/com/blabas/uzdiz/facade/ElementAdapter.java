package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.composite.component.ElementComponent;
import com.blabas.uzdiz.composite.component.Element;
import com.blabas.uzdiz.factory.creator.Factory;
import com.blabas.uzdiz.factory.creator.impl.ShapeFactory;
import com.blabas.uzdiz.factory.product.Shape;
import com.blabas.uzdiz.utils.FileReader;
import com.blabas.uzdiz.utils.RegexMatcher;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementAdapter {

    private FileReader fileReader;
    private RegexMatcher regexMatcher;
    private ArrayList<String> loadedItems;
    private List<ElementComponent> parrentComponents;
    private final int CIRCLE = 3;
    private final int RECTANGLE = 4;
    private Factory shapeFactory;

    public ElementAdapter() {
        fileReader = new FileReader();
        regexMatcher = new RegexMatcher();
        parrentComponents = new ArrayList<>();
        shapeFactory = new ShapeFactory();
    }


    public boolean isFileNameValid(String[] args) {
        return regexMatcher.checkRegex(args);
    }

    public void loadFile() {
        fileReader.readAndParseTxtFile(regexMatcher.getLoadedFileName());
    }

    /**
     * Load txt file and store data to items
     *
     * @return
     */
    public void readFile() {
        this.loadedItems = fileReader.getParsedData();
    }

    //0 - SLOZENI ELEMENT
    //1 - JEDNOSTAVNI ELEMENT
    public void parseParrentItems() {
        ElementComponent parrentElement;
        for (String item : loadedItems) {
            int elementType = parseInt(item.split("   ")[0]);
            if (elementType == 0) {
                parrentElement = new Element();
                parrentElement.setType(parseInt(item.split("   ")[0]));
                parrentElement.setCode(item.split("   ")[1]);
                parrentElement.setParrent(item.split("   ")[2]);
                ArrayList<Integer> coordinates = getCoordinates(item.split("   ")[3]);
                Shape shape = determineShapeType(coordinates, "parrent", null);
                parrentElement.setShape(shape);
                parrentElement.setColor(item.split("   ")[4]);
                parrentComponents.add(parrentElement);
            }
        }
    }

    public List<ElementComponent> parseChildItems() {
        int index = 0;
        for (String item : loadedItems) {
            if (index != 0) {
                String parrentCode = item.split("   ")[2];
                for (ElementComponent parrentComponent : parrentComponents) {
                    if (parrentComponent.getCode().equals(parrentCode)) {
                        ElementComponent childItem = new Element();
                        childItem.setType(parseInt(item.split("   ")[0]));
                        childItem.setCode(item.split("   ")[1]);
                        childItem.setParrent(item.split("   ")[2]);
                        ArrayList<Integer> coordinates = getCoordinates(item.split("   ")[3]);
                        Shape.Point parrentPoint1 = parrentComponent.getShape().getPoints().get(0);


                        Shape shape = determineShapeType(coordinates, "child", parrentPoint1);
                        childItem.setShape(shape);
                        childItem.setColor(item.split("   ")[4]);
                        println("Child: " + childItem.getCode());
                        println("Parretn: " + childItem.getParrent());
                        childIntersectParrent(parrentComponent, childItem);
                        parrentComponent.add(childItem);
                        break;
                    }
                }
            }
            index++;
        }
        return parrentComponents;
    }

    private ArrayList<Integer> getCoordinates(String coordianteJoined) {
        String[] coordianteSeparate = coordianteJoined.split(",");
        ArrayList<Integer> coordinatesData = new ArrayList<>();
        for (String coordinate : coordianteSeparate) {
            coordinatesData.add(parseInt(coordinate));
        }
        return coordinatesData;
    }

    //Uses Factory method
    private Shape determineShapeType(ArrayList<Integer> coordinates, String type, Shape.Point parrentPoint1) {
        Shape shape;
        switch (coordinates.size()) {
            case CIRCLE:
                shape = shapeFactory.createShape("Circle");
                shape.setShapeType("Circle");
                break;
            case RECTANGLE:
                shape = shapeFactory.createShape("Rectangle");
                shape.setShapeType("Rectangle");
                break;
            default:
                shape = shapeFactory.createShape("Polygon");
                shape.setShapeType("Polygon");
                break;
        }
        shape.setPoints(coordinates, type, parrentPoint1);
        return shape;
    }

    private void childIntersectParrent(ElementComponent parrentComponent, ElementComponent childComponent) {


        println("PARRENT x: " + parrentComponent.getShape().getPoints().get(0).getX());
        println("PARRENT y: " + parrentComponent.getShape().getPoints().get(0).getY());
        println("PARRENT x: " + parrentComponent.getShape().getPoints().get(1).getX());
        println("PARRENT y: " + parrentComponent.getShape().getPoints().get(1).getY());

        println("CHILD x: " + childComponent.getShape().getRealShape().getBounds());
        Area a =  ((Area) parrentComponent.getShape().getRealShape());
        Area b =  (Area) childComponent.getShape().getRealShape();
        b.intersect(a);

        if(!b.isEmpty()){
            println("INTERSECT: " + "DA");
        }else{
            println("INTERSECT: " + "NE");
        }



    }


    private int parseInt(String data) {
        return Integer.parseInt(data);
    }

//    public String determineShapeType(){
//        String elementItem = readFile(0);
//        String coordinates = elementItem.split("   ")[3];
//        String[] coordinatesArray = coordinates.split(",");
//        return "Rectangle";
//    }

    public void buildShape(String type) {
        Factory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(type);

    }

}
