package org.collebol.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class is responsible for managing every Client Session that has made a valid connection with the socket.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ClientSession implements AutoCloseable {

    private final Socket socket;
    private final Server server;
    private final OutputStream out;
    private final DataInputStream in;
    private ServerClient player;

    public ClientSession(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.out = socket.getOutputStream();
        this.in = new DataInputStream(socket.getInputStream());
    }

    public void handle() {

    }

    @Override
    public void close() throws Exception {

    }
}
