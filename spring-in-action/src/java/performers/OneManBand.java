package performers;

import exceptions.PerformanceException;

import java.util.Properties;

public class OneManBand implements Performer {

    private Properties instruments;

    public OneManBand() {
    }

    @Override
    public void perform() throws PerformanceException {
        for (Object key : instruments.keySet()) {
            System.out.println(key + ": " + instruments.get(key));
        }
    }

    public void setInstruments(Properties instruments) {
        this.instruments = instruments;
    }
}
