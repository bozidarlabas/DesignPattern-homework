package com.blabas.uzdiz.iterator;

import com.blabas.uzdiz.composite.component.ElementComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bozidar on 02.12.2015..
 */
public class ElementManager implements Container {
    List<ElementComponent> items;
    List<String> allStoredCodes;
    private HashMap<String,Float> colors;

    public ElementManager(){
        colors = new HashMap<>();
    }

    public void setItems(List<ElementComponent> items) {
        this.items = items;
    }

    public void getColors() {
        java.util.Iterator it = colors.entrySet().iterator();
        System.out.println("");
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove();
        }
    }

    public void clearColors(){
        this.colors.clear();
    }

    /**
     * Add area to coresponding color
     * @param color
     * @param area
     */
    public void setColors(String color, float area) {
        if(colors.containsKey(color)){
            //System.out.println("POSTOJI");
            float value = colors.get(color);
            colors.put(color, (value + area));
        }else{
            colors.put(color, area);
            //System.out.println("NISTA");
        }
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
