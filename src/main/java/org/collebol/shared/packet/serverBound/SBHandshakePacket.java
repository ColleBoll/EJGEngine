package org.collebol.shared.packet.serverBound;

import org.collebol.shared.packet.Packet;

import java.util.UUID;

public class SBHandshakePacket extends Packet {

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
}
