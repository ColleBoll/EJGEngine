package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Text;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.shared.math.Vector2D;
import org.lwjgl.opengl.GL11;

/**
 * The ButtonRenderer class is responsible for rendering button components in the UI.
 * It handles the rendering of both standalone buttons and sub-buttons within a parent component.
 * This class uses OpenGL for rendering the button's background, border, and text.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     ButtonRenderer buttonRenderer = new ButtonRenderer(engine);
 *     buttonRenderer.renderButton(buttonId);
 *     buttonRenderer.renderSubButton(subButtonId, parentId);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ButtonRenderer extends Renderer {

    private final EJGEngine engine;

    /**
     * ButtonRenderer constructor.
     *
     * @param engine instance
     */
    public ButtonRenderer(EJGEngine engine) {
        this.engine = engine;
    }

    /**
     * Renders a button with the specified ID.
     * <p>
     * This method retrieves the button component by its ID, and renders its background, border, and text.
     *
     * @param id the ID of the button to render
     */
    public void renderButton(int id) {
        Button button = (Button) this.engine.getComponentHandler().getComponent(Button.class, id);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float x = button.getPosition().getX();
        float y = button.getPosition().getY();
        float width = button.getWidth();
        float height = button.getHeight();
        float borderSize = button.getBorderSize();
        float[] borderColor = button.getBorderColor();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4fv(button.getBackgroundColor());
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y + height);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x + width, y);

        GL11.glEnd();

        if (borderSize > 0) {
            GL11.glLineWidth(borderSize);
            GL11.glColor4fv(borderColor);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(x, y);
            GL11.glVertex2f(x, y + height);
            GL11.glVertex2f(x + width, y + height);
            GL11.glVertex2f(x + width, y);
            GL11.glEnd();
        }

        Text text = button.getText();
        if (text == null) return;
        text.setPosition(new Vector2D(text.getPosition().getX() + x, text.getPosition().getY() + y));

        this.engine.getRenderers().getTextRenderer("default").render(new Text(new Text.TextBuilder()
                .text(text.getText())
                .position(new Vector2D(text.getPosition().getX() + x, text.getPosition().getY() + y))
                .align(text.getAlign())
                .scale(text.getScale())
                .size(text.getSize())
                .rotation(text.getRotation())
        ));
    }

    /**
     * Renders a sub-button with the specified ID within a parent component.
     * <p>
     * This method retrieves the parent component and the sub-button by their IDs, and renders the sub-button's background, border, and text.
     * The sub-button's position is relative to the parent component's position.
     *
     * @param id       the ID of the sub-button to render
     * @param parentId the ID of the parent component
     */
    public void renderSubButton(int id, int parentId) {
        Field parent = (Field) this.engine.getComponentHandler().getComponent(Field.class, parentId);
        Button button = (Button) parent.getSubComponentsHandler().getComponent(Button.class, id);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float x = button.getPosition().getX() + parent.getPosition().getX();
        float y = button.getPosition().getY() + parent.getPosition().getY();
        float width = button.getWidth();
        float height = button.getHeight();
        float borderSize = button.getBorderSize();
        float[] borderColor = button.getBorderColor();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4fv(button.getBackgroundColor());
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y + height);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x + width, y);

        GL11.glEnd();

        if (borderSize > 0) {
            GL11.glLineWidth(borderSize);
            GL11.glColor4fv(borderColor);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(x, y);
            GL11.glVertex2f(x, y + height);
            GL11.glVertex2f(x + width, y + height);
            GL11.glVertex2f(x + width, y);
            GL11.glEnd();
        }

        Text text = button.getText();
        if (text == null) return;

        this.engine.getRenderers().getTextRenderer("default").render(new Text(new Text.TextBuilder()
                .text(text.getText())
                .position(new Vector2D(text.getPosition().getX() + x, text.getPosition().getY() + y))
                .align(text.getAlign())
                .scale(text.getScale())
                .size(text.getSize())
                .rotation(text.getRotation())
        ));
    }
}
