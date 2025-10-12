package org.collebol.client.event;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.MainWindow;

import java.util.ArrayList;
import java.util.List;

public class ClientObserverManager {

    private final EJGEngine engine;
    private final List<EventObserver> observers = new ArrayList<>();

    public ClientObserverManager(EJGEngine engine) {
        this.engine = engine;

        registerDefaultObservers();
    }

    private void registerDefaultObservers() {

    }

    public <O extends EventObserver> void registerObserver(O observer) {
        this.observers.add(observer);
    }

    /**
     * This method will update all the observers in the {@link MainWindow#loop()} method.
     */
    public void updateObservers() {
        for (EventObserver observer : this.observers) {
            observer.observe();
        }
    }
}
