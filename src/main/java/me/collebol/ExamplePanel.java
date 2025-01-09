package me.collebol;

import me.collebol.graphics.TextRenderer;
import me.collebol.gui.Panel;
import me.collebol.utils.PanelLocation;
import org.lwjgl.opengl.GL11;

public class ExamplePanel extends Panel {
    public ExamplePanel(EJGEngine i) {
        super(0, i);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void paint() {
        GL11.glBegin(GL11.GL_TRIANGLES);

        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex2f(-0.5f, -0.5f);

        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex2f(0.5f, -0.5f);

        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(0.0f, 0.5f);

        GL11.glEnd();

        getEngine().getTextRenderer().render("Welcome to EJGEngine!", new PanelLocation(((float) getEngine().WINDOW_WIDTH / 2), 100), 40.0f);
    }
}
