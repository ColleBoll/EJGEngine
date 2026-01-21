package org.collebol.client.event.client.textinput;

import org.collebol.client.EJGEngine;
import org.collebol.client.gui.graphics.ui.component.TextInput;
import org.collebol.shared.event.Event;
import org.collebol.shared.event.EventListener;

/**
 * This event will be called when the fucus changes of a (registered) {@link TextInput} .
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientTextInputFocusEvent implements Event<ClientTextInputFocusEvent.Listener, EJGEngine> {

    private final TextInput textInput;
    private final boolean focused;

    public ClientTextInputFocusEvent(TextInput textInput, boolean focused) {
        this.textInput = textInput;
        this.focused = focused;
    }

    public boolean isFocused(){
        return focused;
    }

    public TextInput getTextInput() {
        return textInput;
    }

    @Override
    public void dispatch(Listener listener, EJGEngine engine) {
        listener.onFocusChange(this, engine);
    }

    public interface Listener extends EventListener {
        void onFocusChange(ClientTextInputFocusEvent event, EJGEngine engine);
    }
}
