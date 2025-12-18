package org.collebol.multiplayer.server;

import org.collebol.multiplayer.packet.clientBound.CBCloseConnectionPacket;
import org.collebol.multiplayer.packet.clientBound.CBStringPacket;
import org.collebol.multiplayer.server.console.ConsoleCommand;
import org.collebol.multiplayer.server.console.commands.ConsoleCloseCommand;
import org.collebol.multiplayer.server.console.commands.ConsoleHelpCommand;
import org.collebol.multiplayer.server.console.commands.ConsoleSendCommand;
import org.collebol.multiplayer.server.console.commands.ConsoleSessionsCommand;

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

    private final Map<String, ConsoleCommand> registeredCommands = new HashMap<>();

    private void registerDefaultCommands() {
        registerCommand("help", new ConsoleHelpCommand());
        registerCommand("sessions", new ConsoleSessionsCommand());
        registerCommand("send", new ConsoleSendCommand());
        registerCommand("close", new ConsoleCloseCommand());
    }

    public void consoleListener() {
        registerDefaultCommands();
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                String[] i = input.split(" ");

                if (i[0].equalsIgnoreCase("stop")) {
                    Server.shutdown();
                    scanner.close();
                    break;
                }

                ConsoleCommand cmd = getRegisteredCommands().getOrDefault(i[0], (args, console) -> incorrectSyntax(args[0]));
                cmd.response(i, this);
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

    public void registerCommand(String name, ConsoleCommand consoleCommand) {
        if (this.registeredCommands == null) return;
        this.registeredCommands.putIfAbsent(name, consoleCommand);
    }

    public Map<String, ConsoleCommand> getRegisteredCommands() {
        return registeredCommands;
    }
}
