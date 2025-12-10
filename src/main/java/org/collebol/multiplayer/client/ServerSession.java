package org.collebol.multiplayer.client;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.clientBound.CBCloseConnectionPacket;
import org.collebol.multiplayer.packet.clientBound.CBHandshakePacket;
import org.collebol.multiplayer.packet.clientBound.CBStringPacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class is responsible for the connection session to the server.
 *
 * @see Session
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ServerSession extends Session implements AutoCloseable {

    private Socket serverSocket;

    private String host;
    private int port;

    public ServerSession() {
        this.host = "localhost";
        this.port = 36676;
    }

    public void setHost(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * With this method you can try to make the connection to the server socket.<br>
     * This is being done with the settet {@link #host} and {@link #port}.<br>
     *
     * <p>Default host:
     * <li>
     *     <ul>Host: {@code localhost}</ul>
     *     <ul>Port: {@code 36676}</ul>
     * </li>
     * </p>
     *
     * @throws IOException if the host does not exist or connection can not been made
     */
    public void connect() {
        try {
            this.serverSocket = new Socket(host, port);
            setOut(new DataOutputStream(serverSocket.getOutputStream()));
            setIn(new DataInputStream(serverSocket.getInputStream()));
            registerDefaultPackets();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        registerPacket(CBHandshakePacket.class);
        registerPacket(CBStringPacket.class);
        registerPacket(CBCloseConnectionPacket.class);
    }

    public boolean isClosed() {
        return serverSocket.isClosed() || !serverSocket.isConnected();
    }

    @Override
    public void close() throws IOException {
        getOut().close();
        getIn().close();
        serverSocket.close();
    }
}
