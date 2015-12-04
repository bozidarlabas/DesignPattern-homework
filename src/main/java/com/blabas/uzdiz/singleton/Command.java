package com.blabas.uzdiz.singleton;

import com.blabas.uzdiz.facade.Validator;
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
public class Command implements OnMenuItemSelected {

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
        String loadedFileName = registry.getArgs()[0];
        if (elementAdapter.isFileNameValid(loadedFileName)) {
            loadFile(registry.provideRegexMatcher().getLoadedFileName());
            List<ElementComponent> parsedComponents = parseLoadedFile();


            elementAdapter.displayAllCodes();



            storeLoadedItems(parsedComponents);
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

//    private void validateItems(List<ElementComponent> parsedComponents){
//        Validator validator = registry.provideValidator();
//        validator.checkCodeDuplicate(parsedComponents);
//    }

    private void storeLoadedItems(List<ElementComponent> allComponents) {
        ElementManager elementManager = registry.provideElementManager();
        elementManager.setItems(allComponents);

    }

    private void loadFile(String fileName) {
        elementAdapter.loadFile(fileName);
        elementAdapter.readFile();
    }

    private List<ElementComponent> parseLoadedFile() {
        elementManager = registry.provideElementManager();
        elementAdapter.parseParrentItems();
        return elementAdapter.parseChildItems();
    }

    @Override
    public void performFirstOperation() {
        println("\nOdabrali ste opciju 1!\n");
        for (ElementComponent component : elementManager.getItems()) {
            component.displayElementInfo();
        }
    }

    @Override
    public void performSecondOperation() {
        println("\nOdabrali ste opciju 2!\n");
        for (ElementComponent component : elementManager.getItems()) {
            component.displayVisibleIntersectedParrentInfo();
        }
    }

    @Override
    public void performThirdOperation(String elementCode, boolean status) {
        println("\nOdabrali ste opciju 3!\n");
        for (ElementComponent component : elementManager.getItems()) {
            component.changeStatus(elementCode, status);
        }
    }

    @Override
    public void performFourthOperation() {
        println("\nOdabrali ste opciju 4!\n");
        for (ElementComponent component : elementManager.getItems()) {
            component.getElementColorSurface();
        }
    }

    @Override
    public void performFifthOperation(String fileName) {
        println("\nOdabrali ste opciju 5!\n");

        if (elementAdapter.isFileNameValid(fileName)) {
            loadFile(fileName);
            List<ElementComponent> parsedComponents = parseLoadedFile();
            storeLoadedItems(parsedComponents);
        }
    }


    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Registry getRegistry() {
        return registry;
    }
}
