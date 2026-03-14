package org.collebol.multiplayer.packet.serverBound;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.client.ServerSession;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.clientBound.CBStringPacket;
import org.collebol.multiplayer.server.ClientSession;
import org.collebol.multiplayer.server.Server;
import org.collebol.shared.event.Event;
import org.collebol.shared.event.EventContext;
import org.collebol.shared.event.EventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SBStringPacket extends Packet<SBStringPacket> implements Event<SBStringPacket.Listener, EventContext> {

    private long currentMilisec;
    private String message;

    public SBStringPacket(long currentMilisec, String message) {
        this.currentMilisec = currentMilisec;
        this.message = message;
    }

    public SBStringPacket() {
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
        ClientSession s = (ClientSession) session;
        Server.getInstance().getEventHandler().call(this, SBStringPacket.Listener.class);
    }

    @Override
    public SBStringPacket receive(DataInputStream in) throws IOException {
        String message = in.readUTF();
        long milisec = in.readLong();
        return new SBStringPacket(milisec, message);
    }

    @Override
    public void send(DataOutputStream out) throws IOException {
        out.writeUTF(getMessage());
        out.writeLong(getCurrentMilisec());
    }

    @Override
    public void dispatch(Listener listener, EventContext context) {
        listener.onPacketReceive(this, (Server) context);
    }

    public interface Listener extends EventListener {
        void onPacketReceive(SBStringPacket event, Server session);
    }
}
