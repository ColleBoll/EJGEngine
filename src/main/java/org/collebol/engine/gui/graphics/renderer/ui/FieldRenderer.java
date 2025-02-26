package org.collebol.engine.gui.graphics.renderer.ui;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.renderer.Renderer;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Button;
import org.collebol.engine.gui.graphics.ui.component.Field;
import org.lwjgl.opengl.GL11;

/**
 * The FieldRenderer class is responsible for rendering field components in the UI.
 * It handles the rendering of both standalone fields and sub-fields within a parent component.
 * This class uses OpenGL for rendering the field's background, border, and any subcomponents it contains.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     FieldRenderer fieldRenderer = new FieldRenderer(engine);
 *     fieldRenderer.renderField(fieldId);
 *     fieldRenderer.renderSubField(subFieldId, parentId);
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class FieldRenderer extends Renderer {

    private final EJGEngine engine;

    /**
     * FieldRenderer constructor.
     *
     * @param engine instance
     */
    public FieldRenderer(EJGEngine engine) {
        this.engine = engine;
    }

    /**
     * Renders a field with the specified ID.
     * <p>
     * This method retrieves the field component by its ID, and renders its background, border, and any subcomponents it contains.
     *
     * @param id the ID of the field to render
     */
    public void renderField(int id) {
        Field field = (Field) this.engine.getComponentHandler().getComponent(Field.class, id);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float x = field.getPosition().getX();
        float y = field.getPosition().getY();
        float width = field.getWidth();
        float height = field.getHeight();
        float borderSize = field.getBorderSize();
        float[] borderColor = field.getBorderColor();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4fv(field.getBackgroundColor());
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

        if (field.subComponents().getComponents() != null) {
            for (Component c : field.subComponents().getComponents().values()) {
                if (c instanceof Field) {
                    this.engine.getRenderers().getUiRenderer().renderSubComponent(Field.class, c.getId(), field.getId());
                }
                if (c instanceof Button) {
                    this.engine.getRenderers().getUiRenderer().renderSubComponent(Button.class, c.getId(), field.getId());
                }
            }
        }
    }

    /**
     * Renders a sub-field with the specified ID within a parent component.
     * <p>
     * This method retrieves the parent component and the sub-field by their IDs, and renders the sub-field's background and border.
     * The sub-field's position is relative to the parent component's position.
     *
     * @param id       the ID of the sub-field to render
     * @param parentId the ID of the parent component
     */
    public void renderSubField(int id, int parentId) {
        Field parent = (Field) this.engine.getComponentHandler().getComponent(Field.class, parentId);
        Field field = (Field) parent.subComponents().getComponent(Field.class, id);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float x = field.getPosition().getX() + parent.getPosition().getX();
        float y = field.getPosition().getY() + parent.getPosition().getY();
        float width = field.getWidth();
        float height = field.getHeight();
        float borderSize = field.getBorderSize();
        float[] borderColor = field.getBorderColor();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4fv(field.getBackgroundColor());
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
    }
}
