package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.composite.component.ElementComponent;
import com.blabas.uzdiz.composite.component.Element;
import com.blabas.uzdiz.factory.creator.Factory;
import com.blabas.uzdiz.factory.creator.impl.ShapeFactory;
import com.blabas.uzdiz.factory.product.Shape;
import com.blabas.uzdiz.utils.FileReader;
import com.blabas.uzdiz.utils.RegexMatcher;

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


    public boolean isFileNameValid(String[] args){
        return regexMatcher.checkRegex(args);
    }

    public void loadFile(){
        fileReader.readAndParseTxtFile(regexMatcher.getLoadedFileName());
    }

    /**
     * Load txt file and store data to items
     * @return
     */
    public void readFile(){
        this.loadedItems = fileReader.getParsedData();
    }

    //0 - SLOZENI ELEMENT
    //1 - JEDNOSTAVNI ELEMENT
    public void parseParrentItems(){
        ElementComponent parrentElement;
        for(String item : loadedItems){
            println("ajmo: " + parseInt(item.split("   ")[0]));
            int elementType = parseInt(item.split("   ")[0]);
            if(elementType == 0){
                parrentElement = new Element();
                parrentElement.setType(parseInt(item.split("   ")[0]));
                parrentElement.setCode(item.split("   ")[1]);
                parrentElement.setParrent(item.split("   ")[2]);
                ArrayList<String> coordinates = getCoordinates(item.split("   ")[3]);
                Shape shape = determineShapeType(coordinates);
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
                        ArrayList<String> coordinates = getCoordinates(item.split("   ")[3]);
                        Shape shape = determineShapeType(coordinates);
                        childItem.setShape(shape);
                        childItem.setColor(item.split("   ")[4]);
                        parrentComponent.add(childItem);
                        break;
                    }
                }
            }
            index++;
        }
        return parrentComponents;
    }

    private ArrayList<String> getCoordinates(String coordianteJoined){
        String[] coordianteSeparate = coordianteJoined.split(",");
        ArrayList<String> coordinatesData = new ArrayList<>();
        for(String coordinate : coordianteSeparate){
            System.out.print("coordinate: " + coordinate + " ");
            coordinatesData.add(coordinate);
        }
        println("");
        println("size of coordinates: " + coordinatesData.size());
        return coordinatesData;
    }

    //Used Factory method
    private Shape determineShapeType(ArrayList<String> coordinates){
        Shape shape;
        switch (coordinates.size()){
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
        shape.setPoints(coordinates);
        return shape;
    }



    private int parseInt(String data){
        return Integer.parseInt(data);
    }

//    public String determineShapeType(){
//        String elementItem = readFile(0);
//        String coordinates = elementItem.split("   ")[3];
//        String[] coordinatesArray = coordinates.split(",");
//        return "Rectangle";
//    }

    public void buildShape(String type){
        Factory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(type);

    }

}
