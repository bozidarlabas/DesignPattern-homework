package com.blabas.uzdiz.iterator;

import com.blabas.uzdiz.composite.component.ElementComponent;

import java.util.List;

/**
 * Created by bozidar on 03.12.2015..
 */
public interface Container {
    public Iterator getIterator(List<ElementComponent> childItems);
}
