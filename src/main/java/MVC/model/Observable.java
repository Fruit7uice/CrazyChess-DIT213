package MVC.model;


/**
 * This interface enables the observer-pattern, if your class is supposed to be observed the class will implement
 * this interface.
 */


public interface Observable {

    void addObserver(Observer o);

    void notify(Observer observer);

    void notifyAllObservers();
}
