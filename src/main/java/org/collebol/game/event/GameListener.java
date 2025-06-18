package org.collebol.game.event;

public abstract class GameListener {

    public abstract void handleEvent(GameEvent event);

    public void onExampleEvent(GameEvent event) {}
}
