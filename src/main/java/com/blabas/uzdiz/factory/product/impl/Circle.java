package com.blabas.uzdiz.factory.product.impl;

import com.blabas.uzdiz.factory.product.Shape;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Circle extends Shape {

    @Override
    public void setPoints(ArrayList<Integer> coordinates, String type, Shape.Point parrentPoint1) {
        setCoordinates(coordinates);
        Point point1 = new Point(coordinates.get(0), coordinates.get(1), coordinates.get(2));
        points.add(point1);


        java.awt.Rectangle rect;
        if(parrentPoint1 != null){
            rect = new java.awt.Rectangle(point1.getX() + parrentPoint1.getX(),point1.getY() + parrentPoint1.getY(),point1.getRadius() ,point1.getRadius());
        }else{
            rect = new java.awt.Rectangle(point1.getX(),point1.getY(),point1.getRadius() ,point1.getRadius());
        }

        Area area = new Area(rect);
        this.setShape(area);


    }

    @Override
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public void calculateSurface() {
        int radius = getCoordinates().get(2);
        double area = radius * radius * Math.PI;
        setArea((float)area);
    }
}