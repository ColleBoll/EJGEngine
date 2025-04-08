package org.collebol.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Server implements AutoCloseable{

    private final String host;
    private final int port;
    private static ServerSocket server;
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private static ServerState serverState;
    private HashMap<UUID, ServerClient> clientList = new HashMap<>();

    public Server(String host, int port, ServerState serverState) throws IOException {
        this.host = host;
        this.port = port;
        this.serverState = serverState;
        this.server = new ServerSocket(port);
    }

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
