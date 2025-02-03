package org.collebol;


import org.collebol.event.EventHandler;
import org.collebol.gui.MainWindow;

public abstract class EJGEngine {

    private MainWindow window;

    private RenderRegisterHandler renderRegisterHandler;
    private EventHandler eventHandler = new EventHandler(this);

    public void start() {
        setup();
        this.window = new MainWindow(this); //here the regiter() method will be called
        this.renderRegisterHandler = new RenderRegisterHandler();
        this.window.registerPanel(new ExamplePanel(this));
        this.window.setPanel(0);
        enable();
        this.window.run();
        disable();
    }

    public abstract void setup();

    public abstract void register();

    public abstract void enable();

    public abstract void disable();

    public MainWindow getWindow() {
        return window;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public RenderRegisterHandler getRenderers() {
        return renderRegisterHandler;
    }
}