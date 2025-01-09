package me.collebol.game;

import me.collebol.game.entity.Player;

public abstract class GameManager implements Runnable {

    private int TICKS = 20;
    public int getTicks() {
        return TICKS;
    }
    public void setTicks(int ticks) {
        this.TICKS = ticks;
    }

    private Player PLAYER;
    public Player getPlayer() {
        return PLAYER;
    }
    public void setPlayer(Player player) {
        this.PLAYER = player;
    }

    private Thread THREAD;
    public void startGameThread(){
        THREAD = new Thread();
        THREAD.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / getTicks();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (THREAD != null){
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
