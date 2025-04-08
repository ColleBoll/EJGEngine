package org.collebol.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class ServerConsole {

    public static void consoleListener() {
        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("help")) {
                    server("Help console command!");
                }
                if (input.equalsIgnoreCase("stop")) {
                    server("Stopping server...");
                    Server.shutdown();
                    scanner.close();
                    break;
                }
            }
        });
        inputThread.start();
    }

    public static void server(String message) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " SERVER]: " + message);
    }

    public static void info(String message) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " INFO]: " + message);
    }

    public static void warn(String warn) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " WARN]: " + warn);
    }
}
