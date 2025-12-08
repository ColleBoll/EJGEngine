package org.collebol.multiplayer.server;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.clientBound.CBHandshakePacket;
import org.collebol.multiplayer.packet.serverBound.SBHandshakePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * This class is responsible for managing every Client Session that has made a valid connection with the socket.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientSession extends Session implements AutoCloseable {

    private final Socket clientSocket;
    private final Server server;

    public ClientSession(Socket socket, Server server) throws IOException {
        this.clientSocket = socket;
        this.server = server;
        setOut(new DataOutputStream(socket.getOutputStream()));
        setIn(new DataInputStream(socket.getInputStream()));
        registerDefaultPackets();
    }


    @Override
    public void handle() throws IOException {
        while (!isClosed()) {
            Packet<?> packet = receive();
            packet.handle(this);
        }
    }

    @Override
    public void registerDefaultPackets() throws IOException {
        registerPacket(SBHandshakePacket.class);
        registerPacket(CBHandshakePacket.class);
    }

    public boolean isClosed() {
        return clientSocket.isClosed() || !clientSocket.isConnected();
    }

    @Override
    public void close() throws Exception {
        getOut().close();
        getIn().close();
        clientSocket.close();
        server.getClientList().remove(this);
        ServerConsole.warn("Client lost connection[IP=" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");
    }
}
