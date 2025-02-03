package org.collebol;

import org.collebol.gui.graphics.renderer.*;

import java.util.HashMap;
import java.util.Map;

public class RenderRegisterHandler {

    private Map<String, TextRenderer> textRenderers;
    private Map<String, TextureRenderer> textureRenderers;
    private CameraRenderer cameraRenderer;
    private WorldRenderer worldRenderer;

    public RenderRegisterHandler() {
        this.textRenderers = new HashMap<>();
        this.textureRenderers = new HashMap<>();
    }

    /**
     * Registers a new renderer. Depending on the type of the renderer, it adds it to the appropriate map or sets it as the camera renderer.
     * <ul>
     *     <li>{@link TextRenderer}</li>
     *     <li>{@link TextureRenderer}</li>
     *     <li>{@link CameraRenderer}</li>
     *     <li>{@link WorldRenderer}</li>
     * </ul>
     *
     * @param renderer The renderer to be registered.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public <T extends Renderer> void registerNewRenderer(T renderer) {
        if (renderer instanceof TextRenderer) {
            textRenderers.put(((TextRenderer) renderer).getName(), (TextRenderer) renderer);
            ((TextRenderer) renderer).setup();
        }
        if (renderer instanceof TextureRenderer) {
            textureRenderers.put(((TextureRenderer) renderer).getName(), (TextureRenderer) renderer);
        }
        if (renderer instanceof CameraRenderer) {
            cameraRenderer = (CameraRenderer) renderer;
        }
        if (renderer instanceof WorldRenderer) {
            worldRenderer = (WorldRenderer) renderer;
        }
    }

    public void registerTextureRenderer(TextureRenderer renderer) {
        this.textureRenderers.put(renderer.getName(), renderer);
    }

    /**
     * Retrieves a TextRenderer by its name. Throws an exception if no TextRenderer is registered or if the specified name is not found.
     *
     * @param name The name of the TextRenderer to retrieve.
     * @return The TextRenderer associated with the given name.
     * @throws RuntimeException if no TextRenderer is registered or if the specified name is not found.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public TextRenderer getTextRenderer(String name) {
        if (this.textRenderers.isEmpty())
            throw new RuntimeException("You are trying to display text, you have not set a TextRenderer yet. Please, register a TextRenderer in the register() method!");
        if (name == "default" && !this.textRenderers.containsKey(name))
            throw new RuntimeException("Please, register a Default font in the register() method with the name 'default'.");
        if (this.textRenderers.get(name) != null) {
            return this.textRenderers.get(name);
        } else {
            throw new RuntimeException("TextRender not found: " + name);
        }
    }

    /**
     * Retrieves a TextureRenderer by its name. Throws an exception if no TextureRenderer is registered or if the specified name is not found.
     *
     * @param name The name of the TextureRenderer to retrieve.
     * @return The TextureRenderer associated with the given name.
     * @throws RuntimeException if no TextureRenderer is registered or if the specified name is not found.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public TextureRenderer getTextureRenderer(String name) {
        if (this.textureRenderers.isEmpty())
            throw new RuntimeException("You are trying to display a Texture but you have not set a TextureRenderer yet. Please, register a TextureRenderer in the register() method!");
        if (this.textureRenderers.get(name) != null) {
            return this.textureRenderers.get(name);
        } else {
            throw new RuntimeException("TextureRenderer not found: " + name);
        }
    }

    /**
     * Retrieves the CameraRenderer. Throws an exception if no CameraRenderer is registered.
     *
     * @return The CameraRenderer.
     * @throws RuntimeException if no CameraRenderer is registered.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public CameraRenderer getCameraRenderer() {
        if (this.cameraRenderer == null)
            throw new RuntimeException("You are trying to display something relative to your Camera using the CameraRenderer, but you have not set a CameraRenderer yet. Please, register a CameraRenderer in the register() method!");
        return cameraRenderer;
    }

    /**
     * Retrieves the WorldRenderer. Throws an exception if no WorldRenderer is registered.
     *
     * @return The WorldRenderer.
     * @throws RuntimeException if no WorldRenderer is registered.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public WorldRenderer getWorldRenderer() {
        if (this.worldRenderer == null)
            throw new RuntimeException("You are trying to display something using a WorldRenderer, but you have not set a WorldRenderer yet. Please, register a WorldRenderer in the register() method!");
        return worldRenderer;
    }
}
