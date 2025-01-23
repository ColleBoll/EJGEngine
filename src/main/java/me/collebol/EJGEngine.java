package me.collebol;


import me.collebol.event.EventHandler;
import me.collebol.gui.graphics.CameraRenderer;
import me.collebol.gui.graphics.TextRenderer;
import me.collebol.gui.MainWindow;
import me.collebol.gui.graphics.TextureRenderer;
import me.collebol.input.MouseHandler;
import me.collebol.utils.Client;

import java.util.HashMap;

public abstract class EJGEngine {

    private MainWindow window;
    private HashMap<String, TextRenderer> textRenderers = new HashMap<>();
    private TextureRenderer textureRenderer;
    private CameraRenderer cameraRenderer;
    private EventHandler eventHandler = new EventHandler(this);

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
        if(this.textRenderers.isEmpty()) throw new RuntimeException("You are trying to display text, you have not set a TextRenderer yet. Please, register a TextRenderer in the register() method!");
        if(name == "default" && !this.textRenderers.containsKey(name)) throw new RuntimeException("Please, register a Default font in the register() method with the name 'default'.");
        if(!(this.textRenderers.get(name) == null)){
            return this.textRenderers.get(name);
        }else{
            throw new RuntimeException("TextRender not found: " + name);
        }
    }

    public TextureRenderer getTextureRenderer() {
        if(this.textureRenderer == null) throw new RuntimeException("You are trying to display a Texture but you have not set a TextureRenderer yet. Please, register a TextureRenderer in the register() method!");
        return textureRenderer;
    }

    public void setTextureRenderer(TextureRenderer textureRenderer) {
        this.textureRenderer = textureRenderer;
    }

    public CameraRenderer getCameraRenderer() {
        if(this.cameraRenderer == null) throw new RuntimeException("You are trying to display something relative to your Camera using the CameraRenderer, but you have not set a CameraRenderer yet. Please, register a CameraRenderer in the register() method!");
        return cameraRenderer;
    }

    public void setCameraRenderer(CameraRenderer objectRenderer) {
        this.cameraRenderer = objectRenderer;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
}