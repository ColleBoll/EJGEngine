package me.collebol.game;

import me.collebol.EJGEngine;
import me.collebol.game.entity.Player;

public abstract class GameManager implements Runnable {

    private EJGEngine ENGINE;
    public EJGEngine getEngine() {
        return this.ENGINE;
    }
    public void setEngine(EJGEngine engine) {
        this.ENGINE = engine;
    }

    private int TICKS = 20;
    public int getTicks() {
        return this.TICKS;
    }
    public void setTicks(int ticks) {
        this.TICKS = ticks;
    }

    private Player PLAYER;
    public Player getPlayer() {
        return this.PLAYER;
    }
    public void setPlayer(Player player) {
        this.PLAYER = player;
    }

    public GameManager(EJGEngine engine){
        this.ENGINE = engine;
    }

    private Thread THREAD;
    public void startGameThread(){
        this.THREAD = new Thread();
        this.THREAD.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / getTicks();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (this.THREAD != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                delta--;
            }
        }
    }

    public abstract void update();
}
