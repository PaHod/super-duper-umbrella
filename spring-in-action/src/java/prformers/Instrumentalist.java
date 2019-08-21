package prformers;

import additionals.intruments.Instrument;
import exceptions.PerformanceException;


public class Instrumentalist implements Performer {

    private String song;
    private Instrument instrument;

    public Instrumentalist(String song, Instrument instrument) {
        this.song = song;
        this.instrument = instrument;
    }

    @Override
    public void perform() throws PerformanceException {
        System.out.println("singing: " + song);
        instrument.play();

    }

    public void setSong(String song) {
        this.song = song;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
