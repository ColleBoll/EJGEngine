package org.collebol.multiplayer.server;

import org.collebol.multiplayer.Session;
import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.serverBound.SBHandshakePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.UUID;

/**
 * This class is responsible for managing every Client Session that has made a valid connection with the server socket.
 *
 * @see Session
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientSession extends Session implements AutoCloseable {

    private final Socket clientSocket;
    private UUID uuid;

    public ClientSession(Socket socket) throws IOException {
        this.clientSocket = socket;
        setOut(new DataOutputStream(socket.getOutputStream()));
        setIn(new DataInputStream(socket.getInputStream()));
        registerDefaultPackets();
    }


    @Override
    public void handle() throws Exception {
        try {
            // to make sure the client sends a handshake packet,
            // the connection will close after 2sec of there is no
            // handshake recieved.
            clientSocket.setSoTimeout(2000);
            ServerConsole.info("Waiting for handshake from client[IP=" + getClientSocket().getInetAddress() + ":" + getClientSocket().getPort() + "]");
            Packet<?> firstPacket = receive();
            if (firstPacket.packetId() == 0) {
                firstPacket.handle(this);
            }
            // if the handshake packet is received within the timespan
            // the while loop starts, and it begins listening for incoming packets
            clientSocket.setSoTimeout(0);
            while (!isClosed()) {
                Packet<?> packet = receive();
                packet.handle(this);
            }
        } catch (SocketTimeoutException e) {
            ServerConsole.error("No handshake received from client[IP=" + getClientSocket().getInetAddress() + ":" + getClientSocket().getPort() + ", TIMEOUT=2000]");
            ServerConsole.warn("Closing client connection because no handshake[IP=" + getClientSocket().getInetAddress() + ":" + getClientSocket().getPort() + "]");
        }
    }

    @Override
    public void registerDefaultPackets() throws IOException {
        registerPacket(SBHandshakePacket.class);
    }

    public boolean isClosed() {
        return clientSocket.isClosed() || !clientSocket.isConnected();
    }

    @Override
    public void close() throws Exception {
        getOut().close();
        getIn().close();
        clientSocket.close();
        Server.getClientList().remove(this);
        ServerConsole.warn("Client lost connection[IP=" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
