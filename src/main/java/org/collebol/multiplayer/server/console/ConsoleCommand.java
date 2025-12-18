package org.collebol.multiplayer.server.console;

import org.collebol.multiplayer.server.ServerConsole;

public interface ConsoleCommand {

    void response(String[] args, ServerConsole console);
}
