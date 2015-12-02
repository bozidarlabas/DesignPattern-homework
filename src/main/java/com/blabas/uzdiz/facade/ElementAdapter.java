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

    public ElementAdapter() {
        fileReader = new FileReader();
        regexMatcher = new RegexMatcher();
        parrentComponents = new ArrayList<>();
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
                parrentElement.setColor(item.split("   ")[4]);
                parrentComponents.add(parrentElement);
            }
        }
    }

    public List<ElementComponent> parseChildItems(){
        int index = 0;
        for(String item : loadedItems){
            if(index != 0){
                String parrentCode = item.split("   ")[2];
                for(ElementComponent parrentComponent : parrentComponents){
                    if(parrentComponent.getCode().equals(parrentCode)){
                        ElementComponent childItem = new Element();
                        childItem.setType(parseInt(item.split("   ")[0]));
                        childItem.setCode(item.split("   ")[1]);
                        childItem.setParrent(item.split("   ")[2]);
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
        shape.draw();
    }

}
