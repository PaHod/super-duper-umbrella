package prformers;

import exceptions.PerformanceException;

public class Juggler implements Performer {
    protected int balls = 3;

    public Juggler() {
    }

    public Juggler(int balls) {
        this.balls = balls;
    }

    @Override
    public void perform() throws PerformanceException {
        System.out.println("jungle " + balls + " balls!");
    }
}
