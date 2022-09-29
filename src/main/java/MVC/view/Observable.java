package MVC.view;

import java.util.List;

public interface Observable {

    void addObserver(Observer o);

    void notify(Observer observer);

    void notifyAll(List<Observer> observers);
}
