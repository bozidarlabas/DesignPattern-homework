package com.blabas.uzdiz.factory.creator;

import com.blabas.uzdiz.factory.product.Shape;

/**
 * Created by bozidar on 02.12.2015..
 */
public abstract class Factory {
    public abstract Shape createShape(String shapeType);
}