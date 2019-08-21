package additionals.intruments;

public class Cymbal implements Instrument {
    @Override
    public void play() {
        System.out.println("playing: " + this.getClass().getSimpleName());
    }
}
