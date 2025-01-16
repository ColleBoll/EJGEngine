package me.collebol;


import me.collebol.gui.graphics.TextRenderer;
import me.collebol.gui.MainWindow;
import me.collebol.gui.Panel;
import me.collebol.gui.graphics.Texture;
import me.collebol.gui.graphics.TextureRenderer;

import java.util.HashMap;

public abstract class EJGEngine {

    private MainWindow window;
    private HashMap<String, TextRenderer> textRenderers = new HashMap<>();
    private TextureRenderer textureRenderer;

    public void start(){
        setup();
        this.window = new MainWindow(this); //here the regiter() method will be called
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

    /**
     * The window the game is displayed on.
     * @return window
     */
    public MainWindow getWindow() {
        return window;
    }

    public void registerTextRenderer(TextRenderer t){
        this.textRenderers.put(t.getName(), t);
        t.setup();
    }
    public TextRenderer getTextRenderer(String name){
        if(this.textRenderers.isEmpty()) throw new RuntimeException("Register a TextRenderer before using!");
        if(!(this.textRenderers.get(name) == null)){
            return this.textRenderers.get(name);
        }else{
            throw new RuntimeException("TextRendere not found: " + name);
        }
    }

    public TextureRenderer getTextureRenderer() {
        if(this.textureRenderer == null) throw new RuntimeException("Set a TextureRenderer before using!");
        return textureRenderer;
    }

    public void setTextureRenderer(TextureRenderer textureRenderer) {
        this.textureRenderer = textureRenderer;
    }
}