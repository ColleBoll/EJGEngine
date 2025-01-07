package me.collebol.engine.internal.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public CardLayout cardLayout;
    public JPanel mainPanel;

    public MainWindow(){

        this.setTitle("GameEngine");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        this.add(mainPanel);

        this.setBackground(Color.BLUE);
    }

    public void showPanel(String panelName){
        cardLayout.show(mainPanel, panelName);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle(panelName);
    }

    public Panel2D getCurrentPanel(){
        return (Panel2D) mainPanel.getComponent(cardLayout.getHgap());
    }
}
