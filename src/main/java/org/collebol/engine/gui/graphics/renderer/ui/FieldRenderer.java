package org.collebol.engine.gui.graphics.renderer.ui;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.gui.graphics.renderer.Renderer;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Field;
import org.lwjgl.opengl.GL11;

public class FieldRenderer extends Renderer {

    private final EJGEngine engine;

    public FieldRenderer(EJGEngine engine) {
        this.engine = engine;
    }

    public void renderField(int id) {
        Field field = (Field) this.engine.getComponentHandler().getComponent(Field.class, id);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glBegin(GL11.GL_QUADS);

        float x = field.getPosition().getX();
        float y = field.getPosition().getY();
        float width = field.getWidth();
        float height = field.getHeight();

        GL11.glColor4fv(field.getBackgroundColor());

        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y + height);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x + width, y);

        GL11.glEnd();

        if(field.getSubComponents().getComponents() != null){
            for (Component c : field.getSubComponents().getComponents().values()) {
                if(c instanceof Field){
                    this.engine.getRenderers().getUiRenderer().renderSubComponent(Field.class, c.getId(), field.getId());
                }
            }
        }
    }

    public void renderSubField(int id, int parentId){
        Field parent = (Field) this.engine.getComponentHandler().getComponent(Field.class, parentId);
        Field field = (Field) parent.getSubComponents().getComponent(Field.class, id);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glBegin(GL11.GL_QUADS);

        float x = field.getPosition().getX() + parent.getPosition().getX();
        float y = field.getPosition().getY() + parent.getPosition().getY();
        float width = field.getWidth();
        float height = field.getHeight();

        GL11.glColor4fv(field.getBackgroundColor());

        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x, y + height);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x + width, y);

        GL11.glEnd();
    }
}
