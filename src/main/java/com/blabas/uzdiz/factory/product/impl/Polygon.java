package com.blabas.uzdiz.factory.product.impl;


import com.blabas.uzdiz.factory.product.Shape;

import java.awt.geom.Area;
import java.util.ArrayList;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Polygon extends Shape {

    @Override
    public void setPoints(ArrayList<Integer> coordinates, String type, Point parrentPoint1) {
        int x = 0;
        int y = 1;
        int[] xCoordinates = new int[coordinates.size()/2];
        int[] yCoordinates = new int[coordinates.size()/2];

        int i = 0;
        for(int coordinate : coordinates){
            if(x < coordinates.size() && y < coordinates.size()){
                Point point2 = new Point(coordinates.get(x), coordinates.get(y));
                if(parrentPoint1 != null){
                    xCoordinates[i] = coordinates.get(x) + parrentPoint1.getX();
                    yCoordinates[i] = coordinates.get(y) + parrentPoint1.getY();
                }else{
                    xCoordinates[i] = coordinates.get(x);
                    yCoordinates[i] = coordinates.get(y);
                }

                points.add(point2);
                x+=2;
                y+=2;
            }
            i++;
        }



        java.awt.Polygon polygon = new java.awt.Polygon(xCoordinates, yCoordinates, coordinates.size()/2);
        Area area = new Area(polygon);
        this.setShape(area);

    }

    @Override
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
}