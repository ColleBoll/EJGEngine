package org.collebol.multiplayer.packet.clientBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CBStringPacket extends Packet<CBStringPacket> {

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
        IO.println("String packet: '" + getMessage() + "' - " + (System.currentTimeMillis() - this.getCurrentMilisec()) + "ms");
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
}
