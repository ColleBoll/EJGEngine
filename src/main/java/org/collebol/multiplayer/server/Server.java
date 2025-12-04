package org.collebol.multiplayer.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private static List<ClientSession> clientList = new ArrayList<>();

    public Server() throws IOException {
        this.host = "localhost";
        this.port = 36676;
        Server.serverState = null;
        serverSocket = new ServerSocket(this.port);
    }

    public Server(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        Server.serverState = null;
        serverSocket = new ServerSocket(port);
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
        starting();
        ServerConsole.server("Server is starting");

        running();
        ServerConsole.server("Server is running");

        serverState = ServerState.RUNNING;

        ServerConsole.server("Server is started at: " + host + ":" + port);

        ServerConsole.consoleListener();

        listen();
    }

    public void listen() {
        try {
            while(serverState == ServerState.RUNNING){
                try {

                    // wacht tot connectie is gemaakt door client
                    Socket clientSocket = serverSocket.accept();

                    // als connectie is gemaakt -> accept
                    accept(clientSocket);

                } catch (IOException e) {
                    if (serverSocket.isClosed()) {
                        break;
                    }
                    throw new RuntimeException(e);
                }
            }
        } finally {
            stopping();
            ServerConsole.server("Server is stopped!");
        }
    }

    private void accept(Socket clientSocket) {

        // als connectie -> submit nieuwe task in thread
        // zodat er meerdere connecties gemaakt kunnen worden
        pool.submit(() -> {

            // maak de session van de gemaakte connectie van de client socket
            try (ClientSession session = new ClientSession(clientSocket, this)) {
                ServerConsole.server("Connection has been made[" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");
                session.handle();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public abstract void starting();

    public abstract void running();

    public abstract void stopping();

    public static void shutdown() {
        try {
            serverState = ServerState.STOPPED;
            pool.shutdown();
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                for (ClientSession s : clientList) {
                    s.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    public List<ClientSession> getClientList() {
        return clientList;
    }
}
