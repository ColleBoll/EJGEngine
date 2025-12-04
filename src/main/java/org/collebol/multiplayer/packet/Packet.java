package org.collebol.multiplayer.packet;

import java.io.Serializable;

public abstract class Packet implements Serializable {

    public abstract int packetId();
}
