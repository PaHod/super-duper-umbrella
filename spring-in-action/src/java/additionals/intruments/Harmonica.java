package additionals.intruments;

public class Harmonica implements Instrument {
    @Override
    public void play() {
        System.out.println("playing: " + this.getClass().getSimpleName());
    }
}
