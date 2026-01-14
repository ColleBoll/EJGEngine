package org.collebol.multiplayer.packet;

import org.collebol.multiplayer.Session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * This abstract class is the base "model" of the packet transfer system of the multiplayer function.
 *
 * <p>A packet contains data to transfer data between the client- and server-side.
 * With multiple methods to send, receive and handle the <code>in</code> and <code>out</code> data.</p>
 *
 * @param <T>
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Packet<T extends Packet<T>> implements Serializable {

    public abstract int packetId();

    public abstract void handle(Session session) throws IOException;

    public abstract T receive(DataInputStream in) throws IOException;

    public abstract void send(DataOutputStream out) throws IOException;
}
