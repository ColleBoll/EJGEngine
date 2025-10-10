package org.collebol.client.event;

import org.collebol.client.EJGEngine;

public interface ClientEvent<L extends ClientListener> {

    void dispatch(L listener, EJGEngine engine);

}
