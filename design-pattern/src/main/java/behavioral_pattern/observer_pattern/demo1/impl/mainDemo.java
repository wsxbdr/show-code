package behavioral_pattern.observer_pattern.demo1.impl;

import behavioral_pattern.observer_pattern.demo1.Message;
import behavioral_pattern.observer_pattern.demo1.impl.observer.ObserverImplDemo1;
import behavioral_pattern.observer_pattern.demo1.impl.observer.ObserverImplDemo2;
import behavioral_pattern.observer_pattern.demo1.impl.subject.SubjectImplDemo;

public class mainDemo {
    public static void main(String[] args) {
        SubjectImplDemo subjectImplDemo = new SubjectImplDemo();
        ObserverImplDemo1 observerImplDemo1 = new ObserverImplDemo1();
        ObserverImplDemo2 observerImplDemo2 = new ObserverImplDemo2();
        subjectImplDemo.registerObserver(observerImplDemo1);
        subjectImplDemo.registerObserver(observerImplDemo2);
        subjectImplDemo.notifyObservers(new Message("hello"));
    }
}
