package org.collebol.client.gui.graphics.renderer.ui;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.Text;
import org.collebol.client.gui.graphics.renderer.Renderer;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.client.gui.graphics.ui.component.Label;
import org.collebol.shared.math.Vector2D;
import org.lwjgl.opengl.GL11;

public class LabelRenderer extends ComponentRenderer<Label> {

    public LabelRenderer(EJGEngine engine) {
        super(engine);
    }

    @Override
    public void renderInternal(Label input, float offsetX, float offsetY) {

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

        Text text = input.getText();
        if (text != null) {

            this.engine.getRenderers().getTextRenderer(input.getTextRenderer()).render(new Text(new Text.TextBuilder()
                    .text(text.getText())
                    .position(new Vector2D(text.getPosition().getX() + x, text.getPosition().getY() + y))
                    .align(text.getAlign())
                    .scale(text.getScale())
                    .size(text.getSize())
                    .rotation(text.getRotation())
            ));
        }
    }
}
