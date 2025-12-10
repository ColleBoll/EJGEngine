package org.collebol.multiplayer.server;

import org.collebol.multiplayer.packet.clientBound.CBCloseConnectionPacket;
import org.collebol.multiplayer.packet.clientBound.CBStringPacket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This ServerConsole class is responsible for managing the server console.
 *
 * <p>With different formats to put text into the console and a console listener.
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class ServerConsole {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void consoleListener() {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                String[] i = input.split(" ");
                if (i[0].equalsIgnoreCase("help")) {
                    server("HELP COMMANDS:");
                    server("- stop: stop the current server.");
                    server("- sessions: list of active sessions");
                    server("- send: send a message to a session");
                    server("- close: force close a session");
                }
                if (i[0].equalsIgnoreCase("stop")) {
                    Server.shutdown();
                    scanner.close();
                    break;
                }
                if (i[0].equalsIgnoreCase("sessions")) {
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
                if (i[0].equalsIgnoreCase("send")) {
                    if (i.length >= 3) {
                        UUID uuid = null;
                        ClientSession session = null;
                        for (ClientSession s : Server.getClientList()) {
                            if (s.getUuid().toString().equals(i[1])) { uuid = UUID.fromString(i[1]); session = s; }
                        }
                        if (uuid != null) {

                            String message = String.join(" ", Arrays.copyOfRange(i, 2, i.length));

                            CBStringPacket packet = new CBStringPacket(message, System.currentTimeMillis());
                            try {
                                session.send(packet);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            server("String packet send[UUID=" + uuid + ", MESSAGE='" + message + "']");

                        } else { error("Given UUID is not a valid session[UUID=" + i[1] + "]"); }
                    } else {
                        incorrectSyntax("send <UUID> <MESSAGE>...");
                    }
                }
                if (i[0].equalsIgnoreCase("close")) {
                    if (i.length >= 2) {
                        UUID uuid = null;
                        ClientSession session = null;
                        for (ClientSession s : Server.getClientList()) {
                            if (s.getUuid().equals(UUID.fromString(i[1]))) { uuid = UUID.fromString(i[1]); session = s; }
                        }
                        if (uuid != null) {

                            try {
                                CBCloseConnectionPacket packet = new CBCloseConnectionPacket(System.currentTimeMillis());
                                session.send(packet);
                                server("Connection Close packet send[UUID=" + uuid + "]");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else { error("Given UUID is not a valid session[UUID=" + i[1] + "]"); }
                    } else {
                        incorrectSyntax("close <UUID>");
                    }
                }
            }
        });
        inputThread.start();
    }

    public static void server(String message) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " SERVER]: " + message + RESET);
    }

    public static void info(String message) {
        System.out.println(BLUE + "[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " INFO]: " + message + RESET);
    }

    public static void warn(String message) {
        System.out.println(YELLOW + "[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " WARN]: " + message + RESET);
    }

    public static void error(String message) {
        System.out.println(RED + "[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " ERROR]: " + message + RESET);
    }

    public static void incorrectSyntax(String syntax) {
        error("Incorrect syntax['" + syntax + "']");
    }
}
