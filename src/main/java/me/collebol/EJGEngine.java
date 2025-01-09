package me.collebol;


import me.collebol.graphics.TextRenderer;
import me.collebol.gui.MainWindow;
import me.collebol.gui.Panel;

public abstract class EJGEngine {

    public String TITLE = "EJGEngine";

    /**
     * Window Frames per Second
     * 0 = infinity / 1 = V-sync / 2 = half of monitor
     * Default = 1 (V-sync)
     */
    public int REFRESH_INTERVAL = 1;
    public int ORIGINAL_TILE_SIZE = 16;
    public int SCALE = 3;
    public int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public int WINDOW_MAX_TILE_WIDTH = 19;
    public int WINDOW_MAX_TILE_HEIGHT = 13;
    public int WINDOW_WIDTH = TILE_SIZE * WINDOW_MAX_TILE_WIDTH;
    public int WINDOW_HEIGHT = TILE_SIZE * WINDOW_MAX_TILE_HEIGHT;

    private MainWindow WINDOW;

    /**
     * The window the game is displayed on.
     * @return window
     */
    public MainWindow getWindow() {
        return WINDOW;
    }

    private TextRenderer TEXT_RENDERER;
    public TextRenderer getTextRenderer(){
        return TEXT_RENDERER;
    }

    public void start(){
        WINDOW = new MainWindow(this);
        this.TEXT_RENDERER = new TextRenderer(this);
        Panel t = new ExamplePanel(this);
        WINDOW.addPanel(t);
        WINDOW.setPanel(0);
        enable();
        WINDOW.run();
        disable();
    }

    public abstract void enable();

    public abstract void disable();
}