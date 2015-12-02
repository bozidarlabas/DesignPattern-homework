package com.blabas.uzdiz.facade;

import com.blabas.uzdiz.factory.creator.Factory;
import com.blabas.uzdiz.factory.creator.impl.ShapeFactory;
import com.blabas.uzdiz.factory.product.Shape;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ShapeMaker {

    public ShapeMaker() {

    }

    public String determineShapeType(){
        return "Circle";
    }

    public void buildShape(String type){
        Factory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(type);
        shape.draw();
    }

}
