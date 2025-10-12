package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Text;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.client.gui.graphics.ui.component.TextInput;
import org.lwjgl.opengl.GL11;
import org.collebol.shared.math.Vector2D;

public class TextInputRenderer extends Renderer {

    private final EJGEngine engine;

    public TextInputRenderer(EJGEngine engine) {
        this.engine = engine;
    }

    public void renderTextInput(int id) {
        renderTextInputInternal(id, 0, 0);
    }

    /**
     * Renders a TextInput as a subcomponent within a parent Field.
     *
     * @param id       the ID of the TextInput
     * @param parentId the ID of the parent Field
     */
    public void renderSubTextInput(int id, int parentId) {
        Field parent = (Field) this.engine.getComponentHandler().getComponent(Field.class, parentId);
        renderTextInputInternal(id, parent.getPosition().getX(), parent.getPosition().getY());
    }

    /**
     * Internal render method used for both standalone and subcomponent TextInputs.
     *
     * @param id     the ID of the TextInput
     * @param offsetX X offset for parent position (0 if standalone)
     * @param offsetY Y offset for parent position (0 if standalone)
     */
    private void renderTextInputInternal(int id, float offsetX, float offsetY) {
        TextInput input = (TextInput) this.engine.getComponentHandler().getComponent(TextInput.class, id);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float x = input.getPosition().getX() + offsetX;
        float y = input.getPosition().getY() + offsetY;
        float width = input.getWidth();
        float height = input.getHeight();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4fv(input.getBackgroundColor());
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y + height);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x + width, y);
        GL11.glEnd();

        if (input.getBorderSize() > 0) {
            GL11.glLineWidth(input.getBorderSize());
            GL11.glColor4fv(input.getBorderColor());
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(x, y);
            GL11.glVertex2f(x, y + height);
            GL11.glVertex2f(x + width, y + height);
            GL11.glVertex2f(x + width, y);
            GL11.glEnd();
        }

        Text text = input.getText();
        if (text != null) {
            float textX = x + 5;
            float textY = y + height / 2 - text.getSize() / 2;

            this.engine.getRenderers().getTextRenderer("default").render(new Text(new Text.TextBuilder()
                    .text(text.getText())
                    .position(new Vector2D(textX, textY))
                    .align(text.getAlign())
                    .scale(text.getScale())
                    .size(text.getSize())
                    .rotation(text.getRotation())
            ));

            if (input.isFocused()) {
                int cursorPos = input.getCursorPosition();
                float cursorX = textX + (cursorPos * text.getSize()) * 0.5f;
                GL11.glLineWidth(1f);
                GL11.glColor4f(0f, 0f, 0f, 1f);
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex2f(cursorX, y + 3);
                GL11.glVertex2f(cursorX, y + height - 3);
                GL11.glEnd();
            }
        }
    }
}
