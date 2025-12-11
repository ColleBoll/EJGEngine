package org.collebol.multiplayer.server;

import org.collebol.multiplayer.packet.clientBound.CBCloseConnectionPacket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This is the server side of the EJGEngine project. This is the base of an external server socket.
 * You can use this for example to make a multiplayer option to run a part of the game external.
 *
 * <p>This class is responsible for setting up and running the server.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class Server implements AutoCloseable {

    private final String host;
    private final int port;
    private static ServerSocket serverSocket;
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private static ServerState serverState;
    private static final List<ClientSession> clientList = new ArrayList<>();
    private static Server instance;

    public Server() throws IOException {
        this.host = "localhost";
        this.port = 36676;
        Server.serverState = null;
        serverSocket = new ServerSocket(this.port);
        instance = this;
    }

    public Server(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        Server.serverState = null;
        serverSocket = new ServerSocket(port);
        instance = this;
    }

    /**
     * Starts the server by performing the following steps:
     * <ol>
     *     <li>Calls the {@link #starting()} method.</li>
     *     <li>Calls the {@link #running()} method.</li>
     *     <li>Sets the {@link #serverState} to {@link ServerState#RUNNING}.</li>
     *     <li>Setting up the {@link ServerConsole#consoleListener()}</li>
     *     <li>Opening the socket.</li>
     * </ol>
     */
    public void start() {
        ServerConsole.server("Server is starting...");
        starting();

        running();
        ServerConsole.server("Server is running!");

        serverState = ServerState.RUNNING;

        ServerConsole.server("Server is started[HOST=" + serverSocket.getLocalSocketAddress() + "]");

        ServerConsole.consoleListener();

        listen();
    }

    /**
     * Listen for incoming connection to the {@link #serverSocket}.<br>
     * If connection has been made the accept() method is called
     * where it tries to make a {@link ClientSession} out of the clientSocket.
     */
    public void listen() {
        try {
            ServerConsole.server("Listening for client connection...");
            while(serverState == ServerState.RUNNING){
                try {

                    Socket clientSocket = serverSocket.accept(); // wait until a client connection has been made

                    ServerConsole.info("Connection has been made[IP=" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");

                    //if connection is been made -> accept()
                    accept(clientSocket);

                } catch (IOException e) {
                    if (serverSocket.isClosed()) {
                        break;
                    }
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void accept(Socket clientSocket) {

        // if connection accept -> submit new task in thread
        // so there can be multiple client connections
        pool.submit(() -> {

            // try to make a session out of the client socket
            try (ClientSession session = new ClientSession(clientSocket)) {
                ServerConsole.info("Client session has been made[IP=" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");
                session.handle();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public abstract void starting();

    public abstract void running();

    public abstract void stopping();

    /**
     * You can use this method to shut down the server with all its sessions.
     */
    public static void shutdown() {
        ServerConsole.server("Stopping server...");
        getInstance().stopping();
        serverState = ServerState.STOPPED;

        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                ServerConsole.server("Server socket closed!");
            }

            // close the active client sessions (the connections of the client on the server)
            CBCloseConnectionPacket closePacket = new CBCloseConnectionPacket(System.currentTimeMillis());
            synchronized (clientList) {
                for (ClientSession session : clientList) {
                    try {
                        // send a packet to the client so it can disconnect is self
                        session.send(closePacket);

                        // if the client does not respond within 2sec the session will be force closed
                        new Thread(() -> {
                            try {
                                Thread.sleep(2000);
                                if (!session.isClosed()) {
                                    ServerConsole.warn("Client did not respond in time, forcing close session[UUID=" + session.getUuid() + "]");
                                    session.close(); // force close client session
                                }
                            } catch (Exception ignored) {}
                        }).start();

                    } catch (Exception e) {
                        ServerConsole.error("Failed to send close packet[ERROR=" + e.getMessage()+"]");
                        session.close();
                    }
                }
            }

            pool.shutdown();
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                ServerConsole.warn("Forcing shutdown of active threads...");
                pool.shutdownNow();
            }

            serverState = ServerState.STOPPED;
            ServerConsole.server("Server is stopped!");

        } catch (Exception e) {
            ServerConsole.error("Shutdown error: ");
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        shutdown();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public static ServerState getServerState() {
        return serverState;
    }

    public static void setServerState(ServerState serverState) {
        Server.serverState = serverState;
    }

    public static List<ClientSession> getClientList() {
        return clientList;
    }

    public static Server getInstance() {
        return instance;
    }
}
