package com.blabas.uzdiz.singleton;

import com.blabas.uzdiz.iterator.ElementManager;
import com.blabas.uzdiz.composite.component.ElementComponent;
import com.blabas.uzdiz.facade.ElementAdapter;
import com.blabas.uzdiz.facade.Menu;
import com.blabas.uzdiz.listener.OnMenuItemSelected;
import com.blabas.uzdiz.registry.Registry;

import java.util.List;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Command implements OnMenuItemSelected{

    private Registry registry;
    private ElementAdapter elementAdapter;
    private ElementManager elementManager;



    /**
     * Static to class instance of the class.
     */
    private static final Command instance = new Command();

    /**
     * Private constructor so nobody can instantiate the class.
     */
    private Command() {
    }

    /**
     * To be called by user to obtain instance of the class.
     *
     * @return instance of the singleton.
     */
    public static Command getInstance() {
        return instance;
    }

    /**
     * First called method in main() for initializing project
     */
    public void initialize() {
        elementAdapter = registry.provideElementAdapter();
        if (elementAdapter.isFileNameValid()) {
            loadFile();
            parseLoadedFile();
            Menu menu = registry.provideMenu();
            menu.setOnMenuClickListener(this);
            menu.choseOption();
        }

        // println("odabrana: " + menu.chosedOption());


//        ElementAdapter elementAdapter = new ElementAdapter();
//        Validator validator = new Validator();
//        if(elementAdapter.isFileNameValid(registry.getArgs())){
//
//            //Element adapter load, read and parse file to composite Tree of Element components
//            elementAdapter.loadFile();
//            elementAdapter.readFile();
//            elementAdapter.parseParrentItems();
//            List<ElementComponent> components = elementAdapter.parseChildItems();
//
//            //Element manager displays data from Tree of Element components
//            ElementManager elementManager = new ElementManager(components);
//            elementManager.getElementList();


        //println("test: " + items.getComponent(0).getCode());
        //validator.validateElement(items);


        //String shapeType = shapeMaker.determineShapeType();
        //shapeMaker.buildShape(shapeType);

//            Rectangle2D.Float rectangle1 = new Rectangle2D.Float(0,0, 10, 10);
//            Rectangle2D.Float rectangle2 = new Rectangle2D.Float(5,5,15,15);
//
//            println("presjek: " + rectangle1.intersects(rectangle2));


        //}
    }

    private void loadFile() {
        elementAdapter.loadFile();
        elementAdapter.readFile();
    }

    private void parseLoadedFile() {
        elementManager = registry.provideElementManager();
        elementAdapter.parseParrentItems();
        List<ElementComponent> allComponents = elementAdapter.parseChildItems();
        ElementManager elementManager = registry.provideElementManager();
        elementManager.setItems(allComponents);
    }

    @Override
    public void performFirstOperation() {
        println("\nOdabrali ste opciju 1!\n");
        for(ElementComponent component : elementManager.getItems()){
            component.displayElementInfo();
        }
    }

    @Override
    public void performSecondOperation() {
        println("\nOdabrali ste opciju 2!\n");
        for(ElementComponent component : elementManager.getItems()){
            component.displayVisibleIntersectedParrentInfo();
        }
    }

    @Override
    public void performThirdOperation(String elementCode, boolean status){
        println("\nOdabrali ste opciju 3!\n");

        for(ElementComponent component : elementManager.getItems()){
            component.changeStatus(elementCode, status);
        }

    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Registry getRegistry() {
        return registry;
    }
}
