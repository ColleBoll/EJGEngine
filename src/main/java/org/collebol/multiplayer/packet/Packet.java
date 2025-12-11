package org.collebol.multiplayer.packet;

import org.collebol.multiplayer.Session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public abstract class Packet<T extends Packet<T>> implements Serializable {

    public abstract int packetId();

    public abstract void handle(Session session) throws IOException;

    public abstract T receive(DataInputStream in) throws IOException;

    public abstract void send(DataOutputStream out) throws IOException;
}
