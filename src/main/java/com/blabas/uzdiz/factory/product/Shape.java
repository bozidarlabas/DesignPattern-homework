package com.blabas.uzdiz.factory.product;

import java.util.ArrayList;

/**
 * Created by bozidar on 02.12.2015..
 */
public abstract class Shape {

    protected ArrayList<Point> points;
    protected String shapeType;
    private java.awt.Shape shape;
    private ArrayList<Integer> coordinates;
    private float area;

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Shape(){
        points = new ArrayList<>();
    }

    public abstract void setPoints(ArrayList<Integer> coordinate, String type, Shape.Point parrentPoint1);

    public abstract void setShapeType(String shapeType);

    public abstract void calculateSurface();

    public void setShape(java.awt.Shape shape) {
        this.shape = shape;
    }

    public java.awt.Shape getRealShape() {
        return shape;
    }

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
