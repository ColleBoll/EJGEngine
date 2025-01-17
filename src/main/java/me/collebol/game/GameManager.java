package me.collebol.game;

import me.collebol.EJGEngine;
import me.collebol.game.objects.entity.Player;

public abstract class GameManager implements Runnable {

    private EJGEngine engine;
    public EJGEngine getEngine() {
        return this.engine;
    }
    public void setEngine(EJGEngine engine) {
        this.engine = engine;
    }

    private int ticks = 20;
    public int getTicks() {
        return this.ticks;
    }
    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    private Player player;
    public Player getPlayer() {
        return this.player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameManager(EJGEngine engine){
        this.engine = engine;
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
