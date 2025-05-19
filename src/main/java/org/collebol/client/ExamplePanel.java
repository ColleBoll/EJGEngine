package org.collebol.client;

import org.collebol.client.gui.Panel;
import org.collebol.shared.math.Vector2D;
import org.lwjgl.opengl.GL11;

public class ExamplePanel extends Panel {
    public ExamplePanel(EJGEngine e) {
        super(0, e);
    }

    @Override
    public void update() {

    }

    @Override
    public void paint() {

        Vector2D pos = new Vector2D((float) getEngine().getWindow().getWidth() / 2, (float) getEngine().getWindow().getHeight() / 2);

        GL11.glBegin(GL11.GL_TRIANGLES);

        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex2f(pos.getX(), pos.getY() - 150);

        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex2f(pos.getX() - 150, pos.getY() + 150);

        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(pos.getX() + 150, pos.getY() + 150);

        GL11.glEnd();
    }
}
