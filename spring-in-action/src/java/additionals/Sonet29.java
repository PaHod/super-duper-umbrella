package additionals;

public class Sonet29 implements Poem {
    private static String[] LINES = {
            "123456",
            "qwerty",
            "asdfgh",
            "zxcvbn"
    };

    public Sonet29() {
    }

    @Override
    public void recite() {
        for (String line : LINES) {
            System.out.println(line);
        }
    }
}
