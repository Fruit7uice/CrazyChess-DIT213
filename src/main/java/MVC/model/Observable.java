package MVC.model;

public interface Observable {

    void addObserver(Observer o);

    void notify(Observer observer);

    void notifyAllObservers();
}