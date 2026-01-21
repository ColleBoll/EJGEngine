package org.collebol.multiplayer.packet.serverBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.clientBound.CBHandshakePacket;
import org.collebol.multiplayer.server.ClientSession;
import org.collebol.multiplayer.server.Server;
import org.collebol.multiplayer.server.ServerConsole;
import org.collebol.shared.event.EventContext;
import org.collebol.shared.event.Event;
import org.collebol.shared.event.EventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * <code>Server Bound Packet</code>
 *
 * <p>
 *     A handshake packet is the start of a healthy connection between the client and server.
 *     It contains multiple data the server uses to register the client.
 *     It handles this packet with adding the client to the {@link Server#getClientList()} and returning a
 *     {@link CBHandshakePacket} to confirm the connection.
 * </p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class SBHandshakePacket extends Packet<SBHandshakePacket> implements Event<SBHandshakePacket.Listener, EventContext> {

    private UUID uuid;
    private String clientIp;
    private long currentMilisec;

    public SBHandshakePacket() {
    }

    public SBHandshakePacket(UUID uuid, long currentMilisec, String clientIp) {
        this.uuid = uuid;
        this.currentMilisec = currentMilisec;
        this.clientIp = clientIp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getClientIp() {
        return clientIp;
    }

    public long getCurrentMilisec() {
        return currentMilisec;
    }

    @Override
    public int packetId() {
        return 0;
    }

    @Override
    public void handle(Session session) throws IOException {
        ClientSession clientSession = (ClientSession) session;

        ServerConsole.info("Handshake packet[UUID="+getUuid()+
                ", IP="+clientSession.getClientSocket().getInetAddress()+":"+clientSession.getClientSocket().getPort()+"] - "+
                (System.currentTimeMillis() - getCurrentMilisec()) + "ms");

        clientSession.setUuid(getUuid());
        Server.getClientList().add(clientSession);

        Server.getInstance().getEventHandler().call(this, SBHandshakePacket.Listener.class);

        CBHandshakePacket response = new CBHandshakePacket(System.currentTimeMillis());
        session.send(response);
    }

    @Override
    public SBHandshakePacket receive(DataInputStream in) throws IOException {
        UUID uuid1 = UUID.fromString(in.readUTF());
        long milisec = in.readLong();
        String clientIp = in.readUTF();

        return new SBHandshakePacket(uuid1, milisec, clientIp);
    }

    @Override
    public void send(DataOutputStream out) throws IOException {
        out.writeUTF(getUuid().toString());
        out.writeLong(getCurrentMilisec());
        out.writeUTF(getClientIp());
    }

    @Override
    public void dispatch(Listener listener, EventContext context) {
        listener.onPacketReceive(this, (Server) context);
    }


    public interface Listener extends EventListener {
        void onPacketReceive(SBHandshakePacket event, Server server);
    }
}
