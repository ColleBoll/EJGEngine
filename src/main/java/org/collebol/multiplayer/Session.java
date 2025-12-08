package org.collebol.multiplayer;

import org.collebol.multiplayer.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class Session {

    private DataOutputStream out;
    private DataInputStream in;

    private final List<Class<? extends Packet<?>>> packets = new ArrayList<>();

    public abstract void handle() throws IOException;

    public abstract void registerDefaultPackets() throws IOException;

    public Packet<?> receive() throws IOException {
        byte packetType = getIn().readByte();
        for (Class<? extends Packet<?>> p : getRegisteredPackets()) {
            try {
                Packet<?> instance = p.getDeclaredConstructor().newInstance();
                if (instance.packetId() == packetType) {
                    return instance.receive(getIn());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void send(Packet<?> packet) throws IOException {
        if (packet == null) return;
        if (getOut() == null) throw new IOException("Output stream not initialized");

        try {
            getOut().write(packet.packetId());
            packet.send(getOut());
            getOut().flush();
        } catch (IOException e) {
            IO.println("Failed to send packet: " + packet.packetId());
            throw e;
        }
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public List<Class<? extends Packet<?>>> getRegisteredPackets() {
        return packets;
    }

    public void registerPacket(Class<? extends Packet<?>> packet) throws IOException {
        if (getRegisteredPackets().contains(packet)) throw new IOException("Packet already registered!");
        getRegisteredPackets().add(packet);
    }
}
