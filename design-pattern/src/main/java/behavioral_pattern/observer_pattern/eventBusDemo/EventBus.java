package behavioral_pattern.observer_pattern.eventBusDemo;

import behavioral_pattern.observer_pattern.eventBusDemo.register.ObserverAction;
import behavioral_pattern.observer_pattern.eventBusDemo.register.ObserverRegistry;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventBus {
    private Executor executor;
    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus() {
        this(Executors.newFixedThreadPool(1));
    }

    protected EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object) {
        registry.register(object);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            executor.execute(() -> {
                observerAction.execute(event);
            });
        }
    }
}
