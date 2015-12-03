package com.blabas.uzdiz.iterator;

import com.blabas.uzdiz.composite.component.ElementComponent;

/**
 * Created by bozidar on 03.12.2015..
 */
public interface Iterator {
    boolean hasNext();

    ElementComponent next();
}
