package org.collebol.multiplayer.server.console.commands;

import org.collebol.multiplayer.server.ClientSession;
import org.collebol.multiplayer.server.Server;
import org.collebol.multiplayer.server.ServerConsole;
import org.collebol.multiplayer.server.console.ConsoleCommand;

import static org.collebol.multiplayer.server.ServerConsole.server;

public class ConsoleSessionsCommand implements ConsoleCommand {
    @Override
    public void response(String[] args, ServerConsole console) {
        server("ACTIVE CLIENT SESSIONS:");
        if (!Server.getClientList().isEmpty()) {
            for (ClientSession s : Server.getClientList()) {
                server("- [IP=" + s.getClientSocket().getInetAddress() + ":" + s.getClientSocket().getPort() +
                        ", UUID=" + s.getUuid() +"]"
                );
            }
        } else {
            server("- none");
        }
    }
}
