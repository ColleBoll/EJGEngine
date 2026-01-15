package org.collebol.shared.event;

import org.collebol.shared.Context;

public interface Event<L extends EventListener, C extends Context> {

    void dispatch(L listener, C context);
}
