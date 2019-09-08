package performers;

import additionals.Poem;
import exceptions.PerformanceException;

public class PoeticJuggler extends Juggler {
    public Poem poem;

    public PoeticJuggler(int balls, Poem poem) {
        super(balls);
        this.poem = poem;
    }

    @Override
    public void perform() throws PerformanceException {
        System.out.println("Juggling " + balls + " while reciting:");
        poem.recite();
    }
}
