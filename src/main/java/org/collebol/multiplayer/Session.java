package org.collebol.multiplayer;

import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.client.ServerSession;
import org.collebol.multiplayer.server.ClientSession;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class representing a single communication session over a socket.<br>
 *
 * A session manages a bidirectional packet stream using a{@link DataInputStream} and {@link DataOutputStream}, and provides
 * methods to {@link #receive()} and {@link #send(Packet)} packets.
 *
 * <p>
 *     Subclasses such as {@link ClientSession} and {@link ServerSession} implement
 *     the concrete behavior for client-side or server-side communication.
 * </p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     MySession session = new MySession();
 *     while (false) { // as long as socket is not closed
 *         session.handle();
 *     }
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Session {

    private DataOutputStream out;
    private DataInputStream in;

    private final List<Class<? extends Packet<?>>> packets = new ArrayList<>();

    /**
     * Reads the next packet from the underlying {@link DataInputStream}.
     * <p>
     * This method reads the packet ID byte, finds a registered {@link Packet}
     * implementation with a matching ID, instantiates it, and delegates the
     * deserialization of the remaining packet data to {@link Packet#receive(DataInputStream)}.
     *
     * @return the fully deserialized Packet, or {@code null} if no matching Packet type exists
     * @throws IOException if reading from the stream fails
     * @throws RuntimeException if a registered Packet type cannot be instantiated
     */

    public Packet<?> receive() throws IOException {
        byte packetType = getIn().readByte(); //first set of packet is always the packet type
        for (Class<? extends Packet<?>> p : getRegisteredPackets()) {
            try {
                //make the base packet as instance
                Packet<?> instance = p.getDeclaredConstructor().newInstance();
                if (instance.packetId() == packetType) {
                    //use the packet instructions to deserialize the incoming stream
                    return instance.receive(getIn());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Sends the given packet over the session's {@link DataOutputStream}.
     * <p>
     * This method first writes the packet's ID byte, then delegates the serialization
     * of the packet's contents to {@link Packet#send(DataOutputStream)}. After the
     * packet is written, the output stream is flushed.
     * </p>
     *
     * @param packet the packet to send; if {@code null}, the method returns without sending anything
     * @throws IOException if the output stream is not initialized or if an I/O error occurs
     */
    public void send(Packet<?> packet) throws IOException {
        if (packet == null) return;
        if (getOut() == null) throw new IOException("Output stream not initialized");

        try {
            getOut().write(packet.packetId()); // write the packet id first
            packet.send(getOut()); //use the packet instructions to serialize the packet
            getOut().flush(); //send
        } catch (IOException e) {
            IO.println("Failed to send packet: " + packet.packetId());
            throw e;
        }
    }

    public abstract void handle() throws Exception;

    public abstract void registerDefaultPackets() throws IOException;

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
