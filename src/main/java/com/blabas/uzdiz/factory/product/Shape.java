package com.blabas.uzdiz.factory.product;

import java.util.ArrayList;

/**
 * Created by bozidar on 02.12.2015..
 */
public abstract class Shape {

    protected ArrayList<Point> points;
    protected String shapeType;

    public Shape(){
        points = new ArrayList<>();
    }

    public abstract void setPoints(ArrayList<String> coordinate);

    public abstract void setShapeType(String shapeType);

    public String getShapeType() {
        return shapeType;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    protected int parseInt(String data){
        return Integer.parseInt(data);
    }

    public class Point{
        private int x;
        private int y;
        private int radius;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getRadius() {
            return radius;
        }
    }
}
