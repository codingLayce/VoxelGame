package fr.layce.engine.instrumenting;

public class Instrumentor {

    private final Timer timer;
    private final boolean showDebug;

    public Instrumentor(boolean debug) {
        this.timer = new Timer(); this.showDebug = debug;
    }

    public void start() {
        this.timer.start();
    }

    public void stop(String msg) {
        long duration = this.timer.stop();
        if (this.showDebug)
            System.out.printf(msg, (float) duration / 1000000f);
    }

}
