package com.blabas.uzdiz.factory.product.impl;


import com.blabas.uzdiz.factory.product.Shape;

import java.util.ArrayList;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Polygon extends Shape {

    @Override
    public void setPoints(ArrayList<String> coordinates) {
        int x = 0;
        int y = 1;
        for(String coordinate : coordinates){
            if(x < coordinates.size() && y < coordinates.size()){
                Point point = new Point(parseInt(coordinates.get(x)), parseInt(coordinates.get(y)));
                points.add(point);
                x+=2;
                y+=2;
            }
        }
    }

    @Override
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
}