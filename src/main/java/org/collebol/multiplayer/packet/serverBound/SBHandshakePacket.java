package org.collebol.multiplayer.packet.serverBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.clientBound.CBHandshakePacket;
import org.collebol.multiplayer.server.ClientSession;
import org.collebol.multiplayer.server.ServerConsole;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class SBHandshakePacket extends Packet<SBHandshakePacket> {

    private final UUID uuid;
    private final String clientIp;
    private final long currentMilisec;

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
        ServerConsole.info("Handshake packet[UUID="+getUuid()+", IP="+getClientIp()+"] - " + (System.currentTimeMillis() - getCurrentMilisec()) + "ms");

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
}
