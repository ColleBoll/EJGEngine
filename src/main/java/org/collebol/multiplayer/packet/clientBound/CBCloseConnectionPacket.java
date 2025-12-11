package org.collebol.multiplayer.packet.clientBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.client.ServerSession;
import org.collebol.multiplayer.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CBCloseConnectionPacket extends Packet<CBCloseConnectionPacket> {

    private long currentMilisec;

    public CBCloseConnectionPacket(long currentMilisec) {
        this.currentMilisec = currentMilisec;
    }

    public CBCloseConnectionPacket() {
    }

    public long getCurrentMilisec() {
        return currentMilisec;
    }

    @Override
    public int packetId() {
        return 2;
    }

    @Override
    public void handle(Session session) throws IOException {
        ServerSession s = (ServerSession) session;
        IO.println("Connection close packet - " + (System.currentTimeMillis() - this.getCurrentMilisec()) + "ms");
        s.close();
    }

    @Override
    public CBCloseConnectionPacket receive(DataInputStream in) throws IOException {
        long milisec = in.readLong();
        return new CBCloseConnectionPacket(milisec);
    }

    @Override
    public void send(DataOutputStream out) throws IOException {
        out.writeLong(getCurrentMilisec());
    }
}
