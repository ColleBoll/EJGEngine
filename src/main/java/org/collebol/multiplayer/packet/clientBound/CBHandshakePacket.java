package org.collebol.multiplayer.packet.clientBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.client.ServerSession;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.serverBound.SBHandshakePacket;
import org.collebol.shared.Context;
import org.collebol.shared.event.Event;
import org.collebol.shared.event.EventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * <code>Client Bound Packet</code>
 *
 * <p>
 *     This packet is received from the server after the {@link SBHandshakePacket} is successfully handled at the server side.
 *     The client session is created at the server.
 * </p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class CBHandshakePacket extends Packet<CBHandshakePacket> implements Event<CBHandshakePacket.Listener, Context> {

    private long currentMilisec;

    public CBHandshakePacket() {
    }

    public CBHandshakePacket(long currentMilisec) {
        this.currentMilisec = currentMilisec;
    }

    public long getCurrentMilisec(){
        return currentMilisec;
    }

    @Override
    public int packetId() {
        return 0;
    }

    @Override
    public void handle(Session session) throws IOException {
        ServerSession s = (ServerSession) session;
        s.getEventHandler().call(this, CBHandshakePacket.Listener.class);
    }

    @Override
    public CBHandshakePacket receive(DataInputStream in) throws IOException {
        long milisec = in.readLong();
        return new CBHandshakePacket(milisec);
    }

    @Override
    public void send(DataOutputStream out) throws IOException {
        out.writeLong(getCurrentMilisec());
    }

    @Override
    public void dispatch(Listener listener, Context context) {
        listener.onPacketReceive(this, (ServerSession) context);
    }

    public interface Listener extends EventListener {
        void onPacketReceive(CBHandshakePacket event, ServerSession session);
    }
}
