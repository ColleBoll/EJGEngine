package org.collebol.multiplayer.server;

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
public class ClientSession implements AutoCloseable {

    private final Socket clientSocket;
    private final Server server;

    private final OutputStream out;
    private final DataInputStream in;

    public ClientSession(Socket socket, Server server) throws IOException {
        this.clientSocket = socket;
        this.server = server;
        this.out = socket.getOutputStream();
        this.in = new DataInputStream(socket.getInputStream());
    }

    public void handle() throws IOException {
        while (!isClosed()) {
            Packet packet = receive();
            switch (packet.packetId()) {
                case 0:
                    SBHandshakePacket handshakePacket = (SBHandshakePacket) packet;
                    ServerConsole.info("Handshake packet[UUID="+handshakePacket.getUuid()+", IP="+clientSocket.getInetAddress()+":"+clientSocket.getPort()+"] - " + (System.currentTimeMillis() - handshakePacket.getCurrentMilisec()) + "ms");

                    CBHandshakePacket response = new CBHandshakePacket(System.currentTimeMillis());
                    send(response);

                    server.getClientList().add(this);
                    break;
                default:
                    ServerConsole.warn("Unknown packet received!");
                    break;
            }
        }
    }

    public Packet receive() throws IOException {
        byte packetType = in.readByte();
        Packet packet;

        switch (packetType) {
            case 0:
                UUID uuid = UUID.fromString(in.readUTF());
                long milisec = in.readLong();
                String clientIp = in.readUTF();

                packet = new SBHandshakePacket(uuid, milisec, clientIp);
                return packet;
            default:
                throw new IOException("Packet not found!");
        }
    }

    public void send(Packet packet) throws IOException {
        DataOutputStream dOut = new DataOutputStream(out);
        dOut.write(packet.packetId());

        switch (packet.packetId()) {
            case 0:
                CBHandshakePacket handshakePacket = (CBHandshakePacket) packet;
                dOut.writeLong(handshakePacket.getCurrentMilisec());
                break;
            default:
                throw new IOException("Unsupported packet type: " + packet.packetId());
        }
        dOut.flush();
    }

    public boolean isClosed() {
        return clientSocket.isClosed() || !clientSocket.isConnected();
    }

    @Override
    public void close() throws Exception {
        out.close();
        in.close();
        clientSocket.close();
        server.getClientList().remove(this);
        ServerConsole.warn("Client lost connection[IP=" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");
    }
}
