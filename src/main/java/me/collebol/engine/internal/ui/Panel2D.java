package me.collebol.engine.internal.ui;

import me.collebol.engine.internal.Internal;

import javax.swing.*;
import java.awt.*;

@Internal
public class Panel2D extends JPanel implements Runnable {

    private int FPS = 60;
    private int ORIGINALTILESIZE = 16;
    private int SCALE = 3;
    private int MAXSCREENROW = 19;
    private int MAXSCREENCOL = 13;
    private Color BACKROUNDCOLOR;
    private String PANELNAME;

    /**
     * This is the "screen" in a 2D dimension. You can print objects within this panel on the preferred refresh rate (FPS).
     * @param panelName The name of the panel.
     */
    public Panel2D(String panelName){
        this.PANELNAME = panelName;

        this.setPreferredSize(new Dimension(getScreenWidth(), getScreenHeight()));
        this.setBackground(BACKROUNDCOLOR);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    /**
     *
     * @return Frames Per Second. The refresh rate of the panel (screen).
     */
    public int getFPS() {
        return FPS;
    }

    /**
     *
     * @param fps Frames Per Second. The refresh rate of the panel (screen).
     */
    public void setFPS(int fps){
        this.FPS = fps;
    }

    /**
     *
     * @return Original Tile Size. Amount of pixels the tile size is set. (Default: 16).
     */
    public int getOriginalTileSize() {
        return ORIGINALTILESIZE;
    }

    /**
     * TIP: set tilesize to size of pixels used on the Tile PNG. If 16, tiles will be displayed on screen: (16 x 16) x scale.
     * @param oTileSize Original Tile Size. The size of every Tile in game.
     */
    public void setOriginalTileSize(int oTileSize){
        this.ORIGINALTILESIZE = oTileSize;
    }

    /**
     * Scale multiplies the Tile size on screen.
     * @return Scale
     */
    public int getScale() {
        return SCALE;
    }

    /**
     * Set the Tile size multiplier on screen.
     * @param scale Scale
     */
    public void setScale(int scale){
        this.SCALE = scale;
    }

    /**
     *
     * @return OriginalTileSize x Scale
     */
    public int getTileSize() {
        return ORIGINALTILESIZE * SCALE;
    }

    public int getMaxScreenRow() {
        return MAXSCREENROW;
    }
    public void setMaxScreenRow(int maxScreenRow){
        this.MAXSCREENROW = maxScreenRow;
    }

    public int getMaxScreenCol() {
        return MAXSCREENCOL;
    }
    public void setMaxScreenCol(int maxScreenCol){
        this.MAXSCREENCOL = maxScreenCol;
    }

    public int getScreenWidth() {
        return getTileSize() * MAXSCREENROW;
    }

    public int getScreenHeight() {
        return getTileSize() * MAXSCREENCOL;
    }

    public Color getBackroundColor(){
        return BACKROUNDCOLOR;
    }
    public void setBackroundColor(Color backroundColor){
        this.BACKROUNDCOLOR = backroundColor;
        this.setBackground(BACKROUNDCOLOR);
    }

    public String getPanelName() {
        return PANELNAME;
    }
    public void setPanelName(String panelName){
        this.PANELNAME = panelName;
    }

    private Thread THREAD;
    public Thread getThread() {
        return THREAD;
    }

    private boolean RUNNING = false;
    public boolean running() {
        return RUNNING;
    }

    public void startThread() {
        RUNNING = true;
        THREAD = new Thread(this);
        THREAD.start();
    }

    public void stopThread() {
        RUNNING = false;
        try {
            getThread().join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (THREAD != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

    }

    private Graphics2D graphics2D;
    public Graphics2D graphics2D() {
        return graphics2D;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
    }
}
