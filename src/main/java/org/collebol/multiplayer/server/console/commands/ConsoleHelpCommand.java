package org.collebol.multiplayer.server.console.commands;

import org.collebol.multiplayer.server.ServerConsole;
import org.collebol.multiplayer.server.console.ConsoleCommand;

import static org.collebol.multiplayer.server.ServerConsole.server;

public class ConsoleHelpCommand implements ConsoleCommand {
    @Override
    public void response(String[] args, ServerConsole console) {
        server("HELP COMMANDS:");
        server("- stop: stop the current server.");
        server("- sessions: list of active sessions");
        server("- send: send a message to a session");
        server("- close: force close a session");
    }
}
