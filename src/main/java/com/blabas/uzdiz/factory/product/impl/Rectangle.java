package com.blabas.uzdiz.factory.product.impl;

import com.blabas.uzdiz.factory.product.Shape;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Rectangle extends Shape {

    @Override
    public void setPoints(ArrayList<Integer> coordinates, String type, Shape.Point parrentPoint1) {
        Point point1 = new Point(coordinates.get(0), coordinates.get(1));
        Point point2 = new Point(coordinates.get(2), coordinates.get(3));
        points.add(point1);
        points.add(point2);

        int width = Math.abs(point2.getX() - point1.getX());
        int height = Math.abs(point2.getY() - point1.getY());

        java.awt.Rectangle rect;
        if(parrentPoint1 != null){
            rect = new java.awt.Rectangle(point1.getX() + parrentPoint1.getX(),point1.getY() + parrentPoint1.getY(),width ,height);
        }else{
            rect = new java.awt.Rectangle(point1.getX(),point1.getY(),width ,height);
        }

        Area area = new Area(rect);
        this.setShape(area);



    }

    @Override
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }
}
//
//coordinates.set(0, coordinates.get(0) + parrentPoint1.getX());
//        coordinates.set(1, coordinates.get(1) + parrentPoint1.getY());