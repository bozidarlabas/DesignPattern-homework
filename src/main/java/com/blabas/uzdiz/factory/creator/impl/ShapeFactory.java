package com.blabas.uzdiz.factory.creator.impl;


import com.blabas.uzdiz.factory.creator.Factory;
import com.blabas.uzdiz.factory.product.Shape;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ShapeFactory extends Factory {
    private String shapePackageName = "com.blabas.uzdiz.factory.product.impl.";

    @Override
    public Shape createShape(String shapeType) {
        Shape shape = null;
            try {
                shape = (Shape) Class.forName(shapePackageName + shapeType).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        return shape;
    }
}
