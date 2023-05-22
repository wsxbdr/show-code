package behavioral_pattern.observer_pattern.demo1.impl.subject;

import behavioral_pattern.observer_pattern.demo1.Message;
import behavioral_pattern.observer_pattern.demo1.Observer;
import behavioral_pattern.observer_pattern.demo1.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectImplDemo implements Subject {
    List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Message message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
