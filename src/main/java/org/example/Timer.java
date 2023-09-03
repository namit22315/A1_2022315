package org.example;
public class Timer {
    private long startTime;
    private boolean isRunning = false;
    public Timer() {
    }
    public void start() {
        if (!this.isRunning) {
            this.startTime = System.currentTimeMillis();
            this.isRunning = true;
        }
    }
    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
        }
    }

    public long getElapsedTime() {
        if (!this.isRunning) {
            long currentTime = System.currentTimeMillis();
            return (currentTime - this.startTime) / 1000L;
        } else {
            return 0L;
        }
    }

    public long displayCurrentElapsedTime() {
        if (this.isRunning) {
            long currentTime = System.currentTimeMillis();
            return (currentTime - this.startTime) / 1000L;
        } else {
            return 0L;
        }
    }
}

