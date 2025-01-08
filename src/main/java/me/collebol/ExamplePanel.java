package me.collebol;

import me.collebol.graphics.text.NanoVGExample;
import me.collebol.gui.Panel;
import org.lwjgl.opengl.GL11;

public class ExamplePanel extends Panel {
    public ExamplePanel(NanoVGExample e) {
        super(0, e);
        this.nanoVGExample = e;
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
    }
}
