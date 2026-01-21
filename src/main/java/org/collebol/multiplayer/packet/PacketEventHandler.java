package org.collebol.multiplayer.packet;

import org.collebol.shared.event.EventContext;
import org.collebol.shared.event.EventHandler;
import org.collebol.multiplayer.server.Server;
import org.collebol.multiplayer.client.ServerSession;

public class PacketEventHandler extends EventHandler<EventContext> {

    /**
     * @param context <br>
     * - The client side uses the {@link Server} context<br>
     * - The server side uses the {@link ServerSession} context
     */
    public PacketEventHandler(EventContext context) {
        super(context);
    }
}
