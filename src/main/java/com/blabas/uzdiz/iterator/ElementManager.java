package com.blabas.uzdiz.iterator;

import com.blabas.uzdiz.composite.component.ElementComponent;

import java.util.List;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementManager implements Container {
    List<ElementComponent> items;
    List<String> allStoredCodes;

    public void setItems(List<ElementComponent> items) {
        this.items = items;
    }


    public List<ElementComponent> getItems() {
        return items;
    }

    public void setAllStoredCodes(String storedCode) {
        this.allStoredCodes.add(storedCode);
    }

    @Override
    public Iterator getIterator(List<ElementComponent> childItems) {
        return new ElementIterator(childItems);
    }

    public class ElementIterator implements Iterator {

        private List<ElementComponent> childItems;

        public ElementIterator(List<ElementComponent> childItems){
            this.childItems = childItems;
        }

        int index;

        @Override
        public boolean hasNext() {
            return index < childItems.size();
        }

        @Override
        public ElementComponent next() {
            if(this.hasNext()){
                return childItems.get(index++);
            }
            return null;
        }
    }
}
