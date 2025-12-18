package org.collebol.multiplayer.server.console.commands;

import org.collebol.multiplayer.packet.clientBound.CBCloseConnectionPacket;
import org.collebol.multiplayer.server.ClientSession;
import org.collebol.multiplayer.server.Server;
import org.collebol.multiplayer.server.ServerConsole;
import org.collebol.multiplayer.server.console.ConsoleCommand;

import java.util.UUID;

import static org.collebol.multiplayer.server.ServerConsole.*;

public class ConsoleCloseCommand implements ConsoleCommand {
    @Override
    public void response(String[] args, ServerConsole console) {
        if (args.length >= 2) {
            UUID uuid = null;
            ClientSession session = null;
            for (ClientSession s : Server.getClientList()) {
                if (s.getUuid().equals(UUID.fromString(args[1]))) { uuid = UUID.fromString(args[1]); session = s; }
            }
            if (uuid != null) {

                try {
                    CBCloseConnectionPacket packet = new CBCloseConnectionPacket(System.currentTimeMillis());
                    session.send(packet);
                    server("Connection Close packet send[UUID=" + uuid + "]");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else { error("Given UUID is not a valid session[UUID=" + args[1] + "]"); }
        } else {
            incorrectSyntax("close <UUID>");
        }
    }
}
