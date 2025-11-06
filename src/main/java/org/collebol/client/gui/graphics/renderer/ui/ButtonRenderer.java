package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Text;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.shared.math.Vector2D;
import org.lwjgl.opengl.GL11;

/**
 * @see ComponentRenderer
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ButtonRenderer extends ComponentRenderer<Button> {


    public ButtonRenderer(EJGEngine engine) {
        super(engine);
    }

    @Override
    void renderInternal(Button input, float offsetX, float offsetY) {

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float x = input.getPosition().getX() + offsetX;
        float y = input.getPosition().getY() + offsetY;
        float width = input.getWidth();
        float height = input.getHeight();
        float borderSize = input.getBorderSize();
        float[] borderColor = input.getBorderColor();

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4fv(input.getBackgroundColor());
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

        Text text = input.getText();
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
