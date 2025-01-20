package me.collebol.game;

import me.collebol.EJGEngine;
import me.collebol.game.objects.entity.Player;

public abstract class GameManager implements Runnable {

    private EJGEngine engine;
    private int ticks = 20;
    private Player player;
    private Thread thread;

    public GameManager(EJGEngine engine){
        this.engine = engine;
    }

    public void startGameThread(){
        this.thread = new Thread();
        this.thread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / getTicks();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (this.thread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1){ //everything in here will be updated at the ticks per second
                update();
                delta--;
            }
        }
    }

    public abstract void update();

    public EJGEngine getEngine() {
        return this.engine;
    }

    public void setEngine(EJGEngine engine) {
        this.engine = engine;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
