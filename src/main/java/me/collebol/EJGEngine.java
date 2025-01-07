package me.collebol;

import me.collebol.engine.internal.ui.MainWindow;
import me.collebol.engine.internal.ui.Panel2D;

public class EJGEngine {

    public static MainWindow window = new MainWindow();
    public static Panel2D testPanel = new Panel2D("test");

    public static void main(String[] args) {
        window.mainPanel.add(testPanel);
        testPanel.startThread();

        window.showPanel("test");
    }

}