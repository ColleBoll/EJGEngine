package org.collebol.client.event.client.listeners;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.client.ClientKeyClickEvent;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.TextInput;
import org.collebol.client.input.KeyType;

public class DefaultKeyClickTextInputListener implements ClientKeyClickEvent.Listener {

    @Override
    public void onKeyClick(ClientKeyClickEvent event, EJGEngine engine) {
        if (event.isReleased()) return;
        for (Component component : engine.getComponentHandler().getComponents().values()) {
            if (component instanceof TextInput) {
                if (((TextInput) component).isFocused()) {
                    if (event.getKeyType() != KeyType.BACKSPACE) {
                        if (event.getKeyType().isAction()) return;
                        ((TextInput) component).getText().setText(
                                ((TextInput) component).getText().getText() + event.getKeyType().getValue()
                        );
                    } else {
                        String currentText = ((TextInput) component).getText().getText();
                        if (!currentText.isEmpty()) {
                            ((TextInput) component).getText().setText(
                                    currentText.substring(0, currentText.length() - 1)
                            );
                        }
                    }
                    ((TextInput) component).setCursorPosition(((TextInput) component).getText().getText().length());
                }
            }
        }
    }
}
