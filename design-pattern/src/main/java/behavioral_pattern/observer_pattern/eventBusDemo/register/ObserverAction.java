package behavioral_pattern.observer_pattern.eventBusDemo.register;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        assert target != null;
        this.target = target;
        this.method = method;
        method.setAccessible(true);
    }

    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
