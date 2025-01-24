package me.collebol;

import me.collebol.gui.graphics.renderer.CameraRenderer;
import me.collebol.gui.graphics.renderer.Renderer;
import me.collebol.gui.graphics.renderer.TextRenderer;
import me.collebol.gui.graphics.renderer.TextureRenderer;

import java.util.HashMap;
import java.util.Map;

public class RenderRegisterHandler {

    private EJGEngine engine;

    private Map<String, TextRenderer> textRenderers;
    private Map<String, TextureRenderer> textureRenderers;
    private CameraRenderer cameraRenderer;

    public RenderRegisterHandler(EJGEngine e){
        this.engine = e;
        this.textRenderers = new HashMap<>();
        this.textureRenderers = new HashMap<>();
    }

    /**
     * Registers a new renderer. Depending on the type of the renderer, it adds it to the appropriate map or sets it as the camera renderer.
     *
     * @param renderer The renderer to be registered.
     */
    public <T extends Renderer> void registerNewRenderer(T renderer){
        if(renderer instanceof TextRenderer){
            textRenderers.put(((TextRenderer) renderer).getName(), (TextRenderer) renderer);
            ((TextRenderer) renderer).setup();
        }
        if(renderer instanceof TextureRenderer){
            textureRenderers.put(((TextureRenderer) renderer).getName(), (TextureRenderer) renderer);
        }
        if(renderer instanceof CameraRenderer){
            cameraRenderer = (CameraRenderer) renderer;
        }
    }

    public void registerTextureRenderer(TextureRenderer renderer){
        this.textureRenderers.put(renderer.getName(), renderer);
    }

    /**
     * Retrieves a TextRenderer by its name. Throws an exception if no TextRenderer is registered or if the specified name is not found.
     *
     * @param name The name of the TextRenderer to retrieve.
     * @return The TextRenderer associated with the given name.
     * @throws RuntimeException if no TextRenderer is registered or if the specified name is not found.
     */
    public TextRenderer getTextRenderer(String name){
        if(this.textRenderers.isEmpty()) throw new RuntimeException("You are trying to display text, you have not set a TextRenderer yet. Please, register a TextRenderer in the register() method!");
        if(name == "default" && !this.textRenderers.containsKey(name)) throw new RuntimeException("Please, register a Default font in the register() method with the name 'default'.");
        if(this.textRenderers.get(name) != null){
            return this.textRenderers.get(name);
        }else{
            throw new RuntimeException("TextRender not found: " + name);
        }
    }

    /**
     * Retrieves a TextureRenderer by its name. Throws an exception if no TextureRenderer is registered or if the specified name is not found.
     *
     * @param name The name of the TextureRenderer to retrieve.
     * @return The TextureRenderer associated with the given name.
     * @throws RuntimeException if no TextureRenderer is registered or if the specified name is not found.
     */
    public TextureRenderer getTextureRenderer(String name){
        if(this.textureRenderers.isEmpty()) throw new RuntimeException("You are trying to display a Texture but you have not set a TextureRenderer yet. Please, register a TextureRenderer in the register() method!");
        if(this.textureRenderers.get(name) != null){
            return this.textureRenderers.get(name);
        }else{
            throw new RuntimeException("TextureRenderer not found: " + name);
        }
    }

    /**
     * Retrieves the CameraRenderer. Throws an exception if no CameraRenderer is registered.
     *
     * @return The CameraRenderer.
     * @throws RuntimeException if no CameraRenderer is registered.
     */
    public CameraRenderer getCameraRenderer(){
        if(this.cameraRenderer == null) throw new RuntimeException("You are trying to display something relative to your Camera using the CameraRenderer, but you have not set a CameraRenderer yet. Please, register a CameraRenderer in the register() method!");
        return cameraRenderer;
    }
}
