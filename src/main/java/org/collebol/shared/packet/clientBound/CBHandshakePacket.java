package org.collebol.shared.packet.clientBound;

import org.collebol.shared.packet.Packet;

public class CBHandshakePacket extends Packet {

    private final long currentMilisec;

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
}
