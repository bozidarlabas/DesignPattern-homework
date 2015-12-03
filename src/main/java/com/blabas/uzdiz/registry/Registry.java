package com.blabas.uzdiz.registry;

import com.blabas.uzdiz.facade.Validator;
import com.blabas.uzdiz.iterator.ElementManager;
import com.blabas.uzdiz.facade.ElementAdapter;
import com.blabas.uzdiz.facade.Menu;
import com.blabas.uzdiz.utils.RegexMatcher;

/**
 * Created by bozidar on 03.12.2015..
 */
public class Registry {

    private String[] args;
    private Menu menu;
    private ElementAdapter elemenentAdapter;
    private ElementManager elementManager;
    private RegexMatcher regexMatcher;

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Menu provideMenu(){
        if(this.menu == null){
            this.menu = new Menu();
        }
        return this.menu;
    }

    public ElementAdapter provideElementAdapter(){
        if(this.elemenentAdapter == null){
            this.elemenentAdapter = new ElementAdapter(this);
        }
        return this.elemenentAdapter;
    }

    public ElementManager provideElementManager(){
        if(this.elementManager == null){
            this.elementManager = new ElementManager();
        }
        return this.elementManager;
    }


    public RegexMatcher provideRegexMatcher(){
        if(this.regexMatcher == null){
            this.regexMatcher = new RegexMatcher();
        }
        return this.regexMatcher;
    }

    public Validator provideValidator(){
       return new Validator();
    }

}
