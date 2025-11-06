package org.collebol.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;
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
public abstract class Server implements AutoCloseable{

    private final String host;
    private final int port;
    private static ServerSocket server;
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private static ServerState serverState;
    private final HashMap<UUID, ServerClient> clientList = new HashMap<>();

    public Server(String host, int port, ServerState serverState) throws IOException {
        this.host = host;
        this.port = port;
        Server.serverState = serverState;
        server = new ServerSocket(port);
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
    public void start(){
        starting();
        ServerConsole.server("Server is starting");

        running();
        ServerConsole.server("Server is running");

        serverState = ServerState.RUNNING;

        ServerConsole.server("Server is starting at: " + host + ":" + port);

        ServerConsole.consoleListener();

        try {
            while(serverState == ServerState.RUNNING){
                try {
                    Socket clientSocket = server.accept();
                    pool.submit(() -> {
                        try (ClientSession session = new ClientSession(clientSocket, this)) {
                            ServerConsole.server("Connection has been made[" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");
                            session.handle();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (IOException e) {
                    if (server.isClosed()) {
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

    public abstract void starting();

    public abstract void running();

    public abstract void stopping();

    /**
     * Shutdown the server.
     */
    public static void shutdown() {
        try {
            serverState = ServerState.STOPPED;
            pool.shutdown();
            if (server != null && !server.isClosed()) {
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
