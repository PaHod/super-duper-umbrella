package additionals.intruments;

public class Piano implements Instrument {
    @Override
    public void play() {
        System.out.println("playing: " + this.getClass().getSimpleName());
    }
}
