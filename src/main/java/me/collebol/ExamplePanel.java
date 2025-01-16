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

        PanelLocation loc = new PanelLocation((float) getEngine().getWindow().getWidth() / 2, (float) getEngine().getWindow().getHeight() / 2);

        GL11.glBegin(GL11.GL_TRIANGLES);

        GL11.glColor3f(1.0f, 0.0f, 0.0f);
        GL11.glVertex2f(loc.x, loc.y - 150);

        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glVertex2f(loc.x - 150, loc.y + 150);

        GL11.glColor3f(0.0f, 0.0f, 1.0f);
        GL11.glVertex2f(loc.x + 150, loc.y + 150);

        GL11.glEnd();
    }
}
