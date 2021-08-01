package fr.layce.engine.instrumenting;

public class Timer {

    private long startTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public long stop() {
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}
