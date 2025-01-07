package me.collebol.engine.internal.ui;

import me.collebol.engine.internal.utils.PanelLocation;

import javax.swing.*;

public class Button extends JButton {

    private PanelLocation panelLocation = new PanelLocation(0, 0);

    public Button(PanelLocation location, int width, int height) {
        this.setBounds(location.x, location.y, width, height);
    }
}
