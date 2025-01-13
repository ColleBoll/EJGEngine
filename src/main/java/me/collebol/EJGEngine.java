package me.collebol;


import me.collebol.gui.graphics.TextRenderer;
import me.collebol.gui.MainWindow;
import me.collebol.gui.Panel;
import me.collebol.gui.graphics.Texture;

public abstract class EJGEngine {

    private MainWindow window;
    /**
     * The window the game is displayed on.
     * @return window
     */
    public MainWindow getWindow() {
        return window;
    }

    private TextRenderer textRenderer;
    public TextRenderer getTextRenderer(){
        return textRenderer;
    }

    public void start(){
        setup();
        this.textRenderer = new TextRenderer(this);
        this.window = new MainWindow(this);
        Panel t = new ExamplePanel(this);
        this.window.addPanel(t);
        this.window.setPanel(0);
        enable();
        this.window.run();
        disable();
    }

    public abstract void setup();

    public abstract void enable();

    public abstract void disable();
}