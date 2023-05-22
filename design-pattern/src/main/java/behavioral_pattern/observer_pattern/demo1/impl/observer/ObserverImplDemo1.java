package behavioral_pattern.observer_pattern.demo1.impl.observer;

import behavioral_pattern.observer_pattern.demo1.Message;
import behavioral_pattern.observer_pattern.demo1.Observer;

public class ObserverImplDemo1 implements Observer {
    @Override
    public void update(Message message) {
        System.out.println("ObserverImplDemo1 is notified!" + message);
    }
}
