package additionals;

import performers.Performer;

import java.util.Map;

public class Entertainer {
    public Performer introduce(Map.Entry<String, Performer> entry) {
        Performer value = entry.getValue();
        System.out.println("\n Entertainer: " +
                "\n Meet next perfomer..... " + entry.getKey() +
                ". He's grate " + value.getClass().getSimpleName() + ".");
        return value;
    }
}
