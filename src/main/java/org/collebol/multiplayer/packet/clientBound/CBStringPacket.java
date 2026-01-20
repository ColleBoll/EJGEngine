package org.collebol.multiplayer.packet.clientBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.client.ServerSession;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.serverBound.SBHandshakePacket;
import org.collebol.multiplayer.server.ClientSession;
import org.collebol.multiplayer.server.Server;
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
 *     This packet is used by the server to send a data string to the client.
 * </p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class CBStringPacket extends Packet<CBStringPacket> implements Event<CBStringPacket.Listener, Context> {

    private long currentMilisec;
    private String message;

    public CBStringPacket(String message, long currentMilisec) {
        this.message = message;
        this.currentMilisec = currentMilisec;
    }

    public CBStringPacket() {
    }

    public long getCurrentMilisec() {
        return currentMilisec;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int packetId() {
        return 1;
    }

    @Override
    public void handle(Session session) throws IOException {
        ServerSession s = (ServerSession) session;
        s.getEventHandler().call(this, CBStringPacket.Listener.class);
    }

    @Override
    public CBStringPacket receive(DataInputStream in) throws IOException {
        String message = in.readUTF();
        long milisec = in.readLong();
        return new CBStringPacket(message, milisec);
    }

    @Override
    public void send(DataOutputStream out) throws IOException {
        out.writeUTF(getMessage());
        out.writeLong(getCurrentMilisec());
    }

    @Override
    public void dispatch(Listener listener, Context context) {
        listener.onPacketReceive(this, (ServerSession) context);
    }

    public interface Listener extends EventListener {
        void onPacketReceive(CBStringPacket event, ServerSession session);
    }
}
