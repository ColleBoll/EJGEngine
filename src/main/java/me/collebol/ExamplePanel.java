package me.collebol;

import me.collebol.gui.Panel;
import me.collebol.utils.PanelLocation;
import org.lwjgl.opengl.GL11;

public class ExamplePanel extends Panel {
    public ExamplePanel(EJGEngine e) {
        super(0, e);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void paint() {

        PanelLocation loc = new PanelLocation(200, 200);

        GL11.glBegin(GL11.GL_TRIANGLES);

        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex2f(loc.x + 100, loc.y + 100);

        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex2f(loc.x, loc.y);

        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(loc.x + 200, loc.y);

        GL11.glEnd();

        getEngine().getTextRenderer().render("Welcome to EJGEngine!", new PanelLocation(((float) getEngine().getWindow().getWidth() / 2), 100), 40.0f);
        getEngine().getTextRenderer().render("A project using OpenGL",
                new PanelLocation(((float) getEngine().getWindow().getWidth() / 2), 550),
                40.0f);
    }
}
