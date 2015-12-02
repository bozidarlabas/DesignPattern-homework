package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.factory.creator.Factory;
import com.blabas.uzdiz.factory.creator.impl.ShapeFactory;
import com.blabas.uzdiz.factory.product.Shape;
import com.blabas.uzdiz.utils.FileReader;
import com.blabas.uzdiz.utils.RegexMatcher;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementAdapter {

    private FileReader fileReader;
    RegexMatcher regexMatcher;

    public ElementAdapter() {
        fileReader = new FileReader();
        regexMatcher = new RegexMatcher();
    }


    public boolean isFileNameValid(String[] args){
        return regexMatcher.checkRegex(args);
    }

    public void loadFile(){
        fileReader.readAndParseTxtFile(regexMatcher.getLoadedFileName());
    }

    public String readFile(int index){
        return fileReader.getParsedData().get(index);
    }

    public String determineShapeType(){
        String elementItem = readFile(0);
        String coordinates = elementItem.split("   ")[3];
        String[] coordinatesArray = coordinates.split(",");


        return "Rectangle";
    }

    public void buildShape(String type){
        Factory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(type);
        shape.draw();
    }

}
