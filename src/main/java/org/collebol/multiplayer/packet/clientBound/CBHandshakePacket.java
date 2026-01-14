package org.collebol.multiplayer.packet.clientBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.serverBound.SBHandshakePacket;

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
public class CBHandshakePacket extends Packet<CBHandshakePacket> {

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
        IO.println("Connection successfully been created - "+(System.currentTimeMillis() - this.getCurrentMilisec()) + "ms");
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

}
