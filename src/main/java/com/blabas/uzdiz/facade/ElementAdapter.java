package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.composite.component.ElementComponent;
import com.blabas.uzdiz.composite.component.Element;
import com.blabas.uzdiz.factory.creator.Factory;
import com.blabas.uzdiz.factory.creator.impl.ShapeFactory;
import com.blabas.uzdiz.factory.product.Shape;
import com.blabas.uzdiz.registry.Registry;
import com.blabas.uzdiz.utils.FileReader;
import com.blabas.uzdiz.utils.RegexMatcher;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementAdapter {

    private FileReader fileReader;
    private RegexMatcher regexMatcher;
    private ArrayList<String> loadedItems;
    private ArrayList<String> correctLoadedItems;
    private List<ElementComponent> parrentComponents;
    private final int CIRCLE = 3;
    private final int RECTANGLE = 4;
    private Factory shapeFactory;
    private Registry registry;
    private Validator validator;
    boolean isFirstElement = false;
    boolean isFirstElementContainer = false;
    private HashMap<String,String> colors;

    private ArrayList<Code> codes;

    public ElementAdapter(Registry registry) {
        fileReader = new FileReader();
        regexMatcher = registry.provideRegexMatcher();
        parrentComponents = new ArrayList<>();
        shapeFactory = new ShapeFactory();
        codes = new ArrayList<>();
        validator = registry.provideValidator();
        correctLoadedItems = new ArrayList<>();
        colors = new HashMap<>();
    }


    public boolean isFileNameValid(String fileName) {
        return regexMatcher.checkRegex(fileName);
    }

    public void loadFile(String fileName) {
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
            parrentElement = new Element();
            int elementType = parseInt(item.split("   ")[0]);
            Code code = new Code();
            String componentCode = item.split("   ")[1];
            String parrentCode = item.split("   ")[2];
            ArrayList<Integer> coord = getCoordinates(item.split("   ")[3]);
            if(validator.checkCoordinates(componentCode, coord)){
                if(componentCode.equals(parrentCode) && !isFirstElementContainer){
                    isFirstElementContainer = true;
                }else if(!componentCode.equals(parrentCode) && !isFirstElementContainer){
                    validator.firstElementNotParrent();
                }

                if(isFirstElementContainer){
                    if (!validator.codeAlreadyExist(codes, componentCode)) {
                        correctLoadedItems.add(item);
                        code.setStoredCode(componentCode);
                        code.setType(elementType);
                        codes.add(code);
                        if (elementType == 0) {
                            parrentElement.setType(parseInt(item.split("   ")[0]));
                            parrentElement.setCode(item.split("   ")[1]);
                            parrentElement.setParrent(item.split("   ")[2]);
                            Shape shape = determineShapeType(coord, "parrent", null);
                            parrentElement.setShape(shape);

                            parrentElement.setColor(item.split("   ")[4]);
                            parrentComponents.add(parrentElement);
                        }
                    }
                }
            }




        }
    }

    public List<ElementComponent> parseChildItems() {
        ElementComponent childItem = new Element();
        boolean correctParrent = false;

        for (String item : correctLoadedItems) {
            if (isFirstElement) {
                String parrentCode = item.split("   ")[2];

                for (ElementComponent parrentComponent : parrentComponents) {

                    if (parrentComponent.getCode().equals(parrentCode)) {
                        correctParrent = true;
                        childItem = new Element();
                        childItem.setType(parseInt(item.split("   ")[0]));
                        childItem.setCode(item.split("   ")[1]);
                        //storedCodes.add(childItem.getCode());
                        childItem.setParrent(parrentCode);
                        ArrayList<Integer> coordinates = getCoordinates(item.split("   ")[3]);
                        Shape.Point parrentPoint1 = parrentComponent.getShape().getPoints().get(0);
                        Shape shape = determineShapeType(coordinates, "child", parrentPoint1);
                        childItem.setShape(shape);
                        childItem.setColor(item.split("   ")[4]);
                        println("Child: " + childItem.getCode());
                        println("Parretn: " + childItem.getParrent());
                        boolean isIntersected = childIntersectParrent(parrentComponent, childItem);
                        childItem.setIntersectParrent(isIntersected);

                        parrentComponent.add(childItem);
                        break;
                    }
                }
                if(!correctParrent){
                    String childCode = item.split("   ")[1];
                    if(validator.existEqualParrentChildCode(childCode, parrentCode)){

                    }
                    else{
                        validator.parrentCodeExistsMessage(parrentCode, childCode);
                    }

                }
                correctParrent = false;

            }
            isFirstElement = true;
        }
        loadedItems.clear();
        correctLoadedItems.clear();
        return parrentComponents;
    }

    public void clearParrentComponents() {
        parrentComponents.clear();
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

    private boolean childIntersectParrent(ElementComponent parrentComponent, ElementComponent childComponent) {


        println("PARRENT x: " + parrentComponent.getShape().getPoints().get(0).getX());
        println("PARRENT y: " + parrentComponent.getShape().getPoints().get(0).getY());
        println("PARRENT x: " + parrentComponent.getShape().getPoints().get(1).getX());
        println("PARRENT y: " + parrentComponent.getShape().getPoints().get(1).getY());

        println("CHILD x: " + childComponent.getShape().getRealShape().getBounds());
        Area a = ((Area) parrentComponent.getShape().getRealShape());
        Area b = (Area) childComponent.getShape().getRealShape();
        b.intersect(a);

        if (!b.isEmpty()) {
            println("INTERSECT: " + "DA");
            return true;

        } else {
            println("INTERSECT: " + "NE");
            return false;

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

    public void displayAllCodes() {
        for (Code code : codes) {
            println("code: " + code.getStoredCode() + " type: " + code.getType());
        }
    }

    public class Code {
        private String storedCode;
        private int type;


        public String getStoredCode() {
            return storedCode;
        }

        public void setStoredCode(String storedCode) {
            this.storedCode = storedCode;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public ArrayList<Code> getCodes() {
        return codes;
    }
}
