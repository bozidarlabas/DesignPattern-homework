package com.blabas.uzdiz.singleton;

import com.blabas.uzdiz.iterator.ElementManager;
import com.blabas.uzdiz.composite.component.ElementComponent;
import com.blabas.uzdiz.facade.ElementAdapter;
import com.blabas.uzdiz.observer.Menu;
import com.blabas.uzdiz.memento.ElementCareTaker;
import com.blabas.uzdiz.memento.ElementOriginator;
import com.blabas.uzdiz.observer.Observer;
import com.blabas.uzdiz.registry.Registry;

import java.util.List;

import static com.blabas.uzdiz.utils.SysoutWrapper.println;

/**
 * Created by bozidar on 02.12.2015..
 */
public class Command extends Observer{

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
            menu.addObserver(this);
            menu.choseOption();
        }

    }

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
        println("\nOdabrali ste opciju 5!\n");

        for (ElementComponent component : elementManager.getItems()) {
            component.getElementsWithBorder();
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

        //Using Memento design pattern for storing and retriving last element state (code and status)
        ElementOriginator originator = registry.provideOriginator();
        ElementCareTaker careTaker = registry.provideCareTaker();

        originator.setState(elementCode, status);
        careTaker.add(originator.saveStateToMemento());
    }

    @Override
    public void performFourthOperation() {
        println("\nOdabrali ste opciju 4!\n");
        for (ElementComponent component : elementManager.getItems()) {
            component.getElementColorSurface();
        }

        elementManager.getColors();
        elementManager.clearColors();



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

    @Override
    public void performSixthOperation() {
        println("\nOdabrali ste opciju 6!\n");
        elementAdapter.displayLastState();
    }

    @Override
    public void performTestOperation() {
        println("\nOdabrali ste opciju TEST!\n");
        for (ElementComponent component : elementManager.getItems()) {
            component.displayElementInfo();
        }
    }


    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Registry getRegistry() {
        return registry;
    }
}
