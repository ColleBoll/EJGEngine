package org.collebol.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerConsole {

    public static void server(String message){
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " SERVER]: " + message);
    }

    public static void info(String message){
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " INFO]: " + message);
    }

    public static void warn(String warn){
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + " WARN]: " + warn);
    }
}
