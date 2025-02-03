package org.collebol.game;

public abstract class GameManager implements Runnable {

    private int ticks = 20;
    private Thread thread;
    private GameRegister gameRegister;

    public GameManager() {
        this.gameRegister = new GameRegister();
    }

    public void startGameThread() {
        this.thread = new Thread();
        this.thread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / getTicks();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (this.thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) { //everything in here will be updated at the ticks per second
                update();
                delta--;
            }
        }
    }

    public abstract void update();

    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void setGameRegister(GameRegister gameRegister) {
        this.gameRegister = gameRegister;
    }

    public GameRegister getGameRegister() {
        if (this.gameRegister == null) throw new RuntimeException("Please, set a GameRegister before using!");
        return this.gameRegister;
    }
}
