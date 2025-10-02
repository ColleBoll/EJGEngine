package org.collebol.game.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameEvent {

    private List<GameListener> listeners = new ArrayList<>();

    public void registerEvent(GameListener listener) {
        this.listeners.add(listener);
    }

    public List<GameListener> getListeners() {
        return this.listeners;
    }

    public void call(Object... params) {
        Map<Class<?>, Object> paramMap = new HashMap<>();
        for (Object param : params) {
            paramMap.put(param.getClass(), param);
        }
        setValues(paramMap);
        for (GameListener listener : this.listeners) {
            listener.handleEvent(this);
        }
    }

    public abstract void setValues(Map<Class<?>, Object> params);
}
