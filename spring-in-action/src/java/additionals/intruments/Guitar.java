package additionals.intruments;

public class Guitar implements Instrument {
    @Override
    public void play() {
        System.out.println("playing: " + this.getClass().getSimpleName());
    }
}
