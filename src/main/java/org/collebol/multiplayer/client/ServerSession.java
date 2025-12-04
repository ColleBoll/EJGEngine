package org.collebol.multiplayer.client;

import org.collebol.multiplayer.packet.Packet;
import org.collebol.multiplayer.packet.clientBound.CBHandshakePacket;
import org.collebol.multiplayer.packet.serverBound.SBHandshakePacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSession implements AutoCloseable {

    private Socket serverSocket;
    private OutputStream out;
    private DataInputStream in;

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
            this.out = serverSocket.getOutputStream();
            this.in = new DataInputStream(serverSocket.getInputStream());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handle() throws IOException {
        while (!isClosed()) {
            Packet packet = receive();
            switch (packet.packetId()) {
                case 0:
                    CBHandshakePacket hp = (CBHandshakePacket) packet;
                    IO.println("Succes, connection: "+(System.currentTimeMillis() - hp.getCurrentMilisec()) + "ms");
                    break;
                default:
                    IO.println("Unknown packet received!");
                    break;
            }
        }
    }

    public Packet receive() throws IOException {
        byte packetType = in.readByte();
        Packet packet;
        switch (packetType) {
            case 0:
                long milisec = in.readLong();
                packet = new CBHandshakePacket(milisec);
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
                SBHandshakePacket handshakePacket = (SBHandshakePacket) packet;
                dOut.writeUTF(handshakePacket.getUuid().toString());
                dOut.writeLong(handshakePacket.getCurrentMilisec());
                dOut.writeUTF(handshakePacket.getClientIp());
                break;
            default:
                throw new IOException("Unsupported packet type: " + packet.packetId());
        }
        dOut.flush();
    }

    public boolean isClosed() {
        return serverSocket.isClosed() || !serverSocket.isConnected();
    }

    @Override
    public void close() throws IOException {
        out.close();
        in.close();
        serverSocket.close();
    }
}
