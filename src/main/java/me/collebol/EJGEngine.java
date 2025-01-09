package me.collebol;


import me.collebol.gui.graphics.TextRenderer;
import me.collebol.gui.MainWindow;
import me.collebol.gui.Panel;
import me.collebol.gui.graphics.TextureRenderer;

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

    private TextureRenderer TEXTURE_RENDERER;
    public TextureRenderer getTextureRenderer(){
        return TEXTURE_RENDERER;
    }

    public void start(){
        setup();
        this.TEXTURE_RENDERER = new TextureRenderer();
        this.TEXT_RENDERER = new TextRenderer(this);
        this.WINDOW = new MainWindow(this);
        Panel t = new ExamplePanel(this);
        this.WINDOW.addPanel(t);
        this.WINDOW.setPanel(0);
        enable();
        this.WINDOW.run();
        disable();
    }

    public abstract void setup();

    public abstract void enable();

    public abstract void disable();
}