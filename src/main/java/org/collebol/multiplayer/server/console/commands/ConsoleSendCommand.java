package org.collebol.multiplayer.server.console.commands;

import org.collebol.multiplayer.packet.clientBound.CBStringPacket;
import org.collebol.multiplayer.server.ClientSession;
import org.collebol.multiplayer.server.Server;
import org.collebol.multiplayer.server.ServerConsole;
import org.collebol.multiplayer.server.console.ConsoleCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static org.collebol.multiplayer.server.ServerConsole.*;

public class ConsoleSendCommand implements ConsoleCommand {
    @Override
    public void response(String[] args, ServerConsole console) {
        if (args.length >= 3) {
            UUID uuid = null;
            ClientSession session = null;
            for (ClientSession s : Server.getClientList()) {
                if (s.getUuid().toString().equals(args[1])) { uuid = UUID.fromString(args[1]); session = s; }
            }
            if (uuid != null) {

                String message = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

                CBStringPacket packet = new CBStringPacket(message, System.currentTimeMillis());
                try {
                    session.send(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                server("String packet send[UUID=" + uuid + ", MESSAGE='" + message + "']");

            } else { error("Given UUID is not a valid session[UUID=" + args[1] + "]"); }
        } else {
            incorrectSyntax("send <UUID> <MESSAGE>...");
        }
    }
}
