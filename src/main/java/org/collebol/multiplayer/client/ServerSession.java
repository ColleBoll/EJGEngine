package org.collebol.multiplayer.client;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.clientBound.CBHandshakePacket;
import org.collebol.multiplayer.packet.serverBound.SBHandshakePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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

    public void connect() {
        try {
            this.serverSocket = new Socket(host, port);
            setOut(new DataOutputStream(serverSocket.getOutputStream()));
            setIn(new DataInputStream(serverSocket.getInputStream()));
            registerDefaultPackets();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
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
        registerPacket(SBHandshakePacket.class);
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
