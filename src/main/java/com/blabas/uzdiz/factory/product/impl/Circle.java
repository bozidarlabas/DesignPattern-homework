package com.blabas.uzdiz.factory.product.impl;

import com.blabas.uzdiz.factory.product.Shape;

import java.util.ArrayList;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Circle extends Shape {

    @Override
    public void setPoints(ArrayList<String> coordinates) {
        points.add(new Point(parseInt(coordinates.get(0)), parseInt(coordinates.get(1)), parseInt(coordinates.get(2))));
    }

    @Override
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
}